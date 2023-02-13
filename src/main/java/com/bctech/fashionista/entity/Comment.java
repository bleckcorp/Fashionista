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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    private Boolean isApproved = false;

    @Column(nullable = false)
    private AuthorType authorType;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
