package com.example.jjapjun.dao;

import com.example.jjapjun.role.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter @Setter
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String userName;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 45)
    private String email;

    @ColumnDefault("'ROLE_USER'")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @CreationTimestamp
    private Timestamp createDate;
}
