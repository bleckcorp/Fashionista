package com.bctech.fashionista.entity;

import com.bctech.fashionista.constants.AuthorType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    private AuthorType authorType = AuthorType.VISITOR;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;



    @OneToMany(cascade=CascadeType.ALL, mappedBy = "visitor", fetch = FetchType.EAGER)
    private List<Likes> likesList  = new ArrayList<Likes>();


    @OneToMany(cascade=CascadeType.ALL, mappedBy = "visitor", fetch = FetchType.EAGER)
    private List<Comment> commentList  = new ArrayList<Comment>();
}
