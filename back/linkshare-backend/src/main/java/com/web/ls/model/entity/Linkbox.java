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
@Table(name = "linkbox")
public class Linkbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boxid", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "view_count", nullable = false)
    private Integer viewCount;

    @Lob
    @Column(name = "`desc`")
    private String desc;

    @CreationTimestamp
    @Column(name = "regtime", nullable = false, updatable = false)
    private Instant regtime;

}