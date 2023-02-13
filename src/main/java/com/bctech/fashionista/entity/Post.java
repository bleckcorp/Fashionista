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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String imagePath;

    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updatePostDate;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.EAGER)
    private List<Likes> likesList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")

    )
    private Set<Categories> categories = new HashSet<>();

    public void addCategory(Categories category) {
        this.categories.add(category);
        category.getPosts().add(this);
    }

    public void removeCategory(long categoryId) {
        Categories category = this.categories.stream().filter(t -> t.getId() == categoryId).findFirst().orElse(null);
        if (category != null) {
            this.categories.remove(category);
            category.getPosts().remove(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return id != null && Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
