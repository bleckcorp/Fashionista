package com.bctech.fashionista.repository;

import com.bctech.fashionista.entity.Likes;
import com.bctech.fashionista.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    List<Likes> findAllByPost(Post post);

    Optional<Likes> findByVisitorIdAndPostId(Long visitorId, Long postId);
}
