package com.bctech.fashionista.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends BaseEntity {
    private String title;
    private String content;
    private String imagePath;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.EAGER)
    private List<Likes> likesList = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.EAGER)
    private List<Categories> categories = new ArrayList<>();


}
