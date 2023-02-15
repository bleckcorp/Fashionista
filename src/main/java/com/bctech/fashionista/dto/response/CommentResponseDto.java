package com.bctech.fashionista.dto.response;

import com.bctech.fashionista.constants.AuthorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private String content;
    private Long id;
    private AuthorType authorType;
}
