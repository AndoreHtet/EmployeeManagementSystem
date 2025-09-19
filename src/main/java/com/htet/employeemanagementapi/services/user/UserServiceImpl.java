package com.htet.employeemanagementapi.services.user;

import com.htet.employeemanagementapi.dto.user.UserDTO;
import com.htet.employeemanagementapi.dto.user.UserDetail;
import com.htet.employeemanagementapi.dto.user.UserSearchDTO;
import com.htet.employeemanagementapi.entities.User;
import com.htet.employeemanagementapi.repositories.UserRepo;
import com.htet.employeemanagementapi.services.mail.EmailService;
import com.htet.employeemanagementapi.services.table.TableService;
import com.htet.employeemanagementapi.util.api.payload.TableResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final TableService tableService;

    private final PasswordEncoder bCryptPasswordEncoder;

    private final EmailService emailService;

    @Override
    public Optional<UserDTO> getUserDetailByEmail(String email) {
        Function<CriteriaBuilder, CriteriaQuery<UserDTO>> searchByEmail = cb -> {
            var cq = cb.createQuery(UserDTO.class);
            var root = cq.from(User.class);
            cq.select(cb.construct(
                    UserDTO.class,
                    root.get("email"),
                    root.get("password"),
                    root.get("role").get("name")
            ));
            cq.where(cb.equal(root.get("email"), email));
            return cq;
        };
        return userRepo.findOne(searchByEmail);
    }

    @Override
    public TableResponse<UserDetail> userList(UserSearchDTO searchDTO) {
        var queryFunction = userSearchQuery(searchDTO);

        Function<CriteriaBuilder, CriteriaQuery<Long>> countFunction = cb -> {
            var cq = cb.createQuery(Long.class);
            var root = cq.from(User.class);
            cq.select(cb.count(root.get("id")));
            cq.where(searchDTO.predicates(cb , root));
            return cq;
        };

        var userList = userRepo.findAll(queryFunction,countFunction,searchDTO.getPageNo(),searchDTO.getPageSize());
        return new TableResponse<>(userRepo.count(),userList.getTotalElements(),
                userList.getTotalPages(),userList.getContent());
    }

    @Override
    @Transactional
    public void resetPassword(String email,String optCode, String password) throws BadRequestException {
        var user = userRepo.findByEmail(email)
                .orElseThrow(
                        () -> new BadRequestException("User not found!")
                );

        if (user.getResetTokenExpiration().isBefore(Instant.now())){
            throw new BadRequestException("OTP Code is expired!");
        }

        if (!user.getOptCode().equals(optCode)){
            throw new BadRequestException("OTP Code does not match!");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void redeemPassword(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid login credentials")
                );

        String optCode = String.format("%06d", new Random().nextInt(999999));
        Instant expiredTime = Instant.now().plusSeconds(900);
        user.setOptCode(optCode);
        user.setResetTokenExpiration(expiredTime);
        userRepo.save(user);
        emailService.sendPasswordResetEmail(email,optCode);


    }

    Function<CriteriaBuilder, CriteriaQuery<UserDetail>> userSearchQuery(UserSearchDTO searchDTO) {
        return cb -> {
            var cq = cb.createQuery(UserDetail.class);
            var root = cq.from(User.class);
            UserDetail.select(cq , root);
            tableService.sort(cb,cq,root,searchDTO.getSortColumnName(),searchDTO.getSortDirection());
            cq.where(searchDTO.predicates(cb , root));
            return cq;
        };
    }
}
