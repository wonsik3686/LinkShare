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
@Table(name = "user_interest")
public class UserInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "uid", nullable = false)
    private Integer uid;

    @Column(name = "interest_id", nullable = false)
    private Integer interestId;

    @Column(name = "regtime", nullable = false)
    private Instant regtime;
}