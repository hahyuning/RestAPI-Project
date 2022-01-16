package com.todo.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Getter
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id; // 사용자에게 고유하게 부여되는 id

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email; // 아이디 역할

    @Column(nullable = false)
    private String password;
}
