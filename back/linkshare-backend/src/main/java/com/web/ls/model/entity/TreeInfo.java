package com.web.ls.model.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tree_info")
public class TreeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "boxid", nullable = false)
    private Integer boxid;

    @Lob
    @Column(name = "json")
    private String json;
}