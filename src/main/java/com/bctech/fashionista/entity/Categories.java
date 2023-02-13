package com.bctech.fashionista.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE},
            mappedBy = "categories")
    private Set<Post> posts;


    public void addPost(Post post) {
        this.posts.add(post);
        post.getCategories().add(this);
    }

    public void removePost(Post post) {
        this.posts.remove(post);
        post.getCategories().remove(this);
    }


}

