package com.htet.employeemanagementapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseField{

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "email", length = 45, nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @JoinColumn(name = "user_role_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_role"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
}
