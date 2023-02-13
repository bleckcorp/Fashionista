package com.bctech.fashionista.dto.response;

import com.bctech.fashionista.entity.Categories;
import com.bctech.fashionista.entity.Comment;
import com.bctech.fashionista.entity.Likes;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String imagePath;
    private LocalDateTime creationDate;
    private LocalDateTime updatePostDate;

}
