package com.sameer.medifind_backend.user.entity;


import com.sameer.medifind_backend.common.entity.BaseEntity;
import com.sameer.medifind_backend.user.enums.AccountStatus;
import com.sameer.medifind_backend.user.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "phone")
        }
)
public class User extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

}
