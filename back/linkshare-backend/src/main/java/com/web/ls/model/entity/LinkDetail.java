package com.web.ls.model.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "link_detail")
public class LinkDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linkid", nullable = false)
    private Integer id;

    @Column(name = "boxid", nullable = false)
    private Integer boxid;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "`desc`")
    private String desc;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Lob
    @Column(name = "url")
    private String url;

}