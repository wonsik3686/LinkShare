package com.web.ls.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "box_comment")
public class BoxComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "uid", nullable = false)
    private Integer uid;

    @Column(name = "boxid", nullable = false)
    private Integer boxid;

    @Lob
    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "regtime", nullable = false)
    private Instant regtime;

}