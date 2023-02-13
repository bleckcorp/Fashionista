package com.bctech.fashionista.dto.request;

import com.bctech.fashionista.constants.AuthorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentRequestDto {
    private String content;
    private Long postId;
    private Long adminId;
    private String visitorName;
    private String visitorEmail;

}
