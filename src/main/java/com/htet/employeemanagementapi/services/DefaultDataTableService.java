package com.htet.employeemanagementapi.services;

import com.htet.employeemanagementapi.dto.searchData.DataTableSearchDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DefaultDataTableService {

    public <T> Pageable getPageable(T obj) {
        if (DataTableSearchDTO.class.isAssignableFrom(obj.getClass())) {
            DataTableSearchDTO reqDto = (DataTableSearchDTO) obj;
            var sort = Sort.by(
                    reqDto.getDir().equals("asc")
                            ? Sort.Direction.ASC : Sort.Direction.DESC,
                    reqDto.getColumnName());

            var pageNo = reqDto.getPageNo() == null || reqDto.getPageNo() == 0
                    ? reqDto.getStart() / reqDto.getLength()
                    : reqDto.getPageNo();

            return PageRequest.of(pageNo, reqDto.getLength(), sort);
        }
        return null;
    }

    public int getPageNo(DataTableSearchDTO dto) {
        return dto.getPageNo() == null || dto.getPageNo() == 0
                ? dto.getStart() / dto.getLength()
                : dto.getPageNo();
    }
}
