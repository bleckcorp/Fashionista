package com.bctech.fashionista.entity;

import com.bctech.fashionista.constants.AuthorType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends BaseEntity {


    private String content;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    @Column(nullable = false)
    private AuthorType authorType;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
