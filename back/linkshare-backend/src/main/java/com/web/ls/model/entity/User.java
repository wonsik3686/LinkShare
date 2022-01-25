package com.web.ls.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Integer id;

    @Column(name = "isAdmin", nullable = false)
    private Boolean isAdmin = false;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Lob
    @Column(name = "introduce")
    private String introduce;

    @Column(name = "regtime", nullable = false)
    private Instant regtime;

    @Column(name = "image_path")
    private String imagePath;

}