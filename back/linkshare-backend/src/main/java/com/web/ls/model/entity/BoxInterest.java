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
@Table(name = "box_interest")
public class BoxInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "boxid", nullable = false)
    private Integer boxid;

    @Column(name = "interest_id", nullable = false)
    private Integer interestId;

    @CreationTimestamp
    @Column(name = "regtim", nullable = false, updatable = false)
    private Instant regtim;
}