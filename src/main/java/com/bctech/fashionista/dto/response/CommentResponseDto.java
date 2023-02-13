package com.bctech.fashionista.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentResponseDto {
    private String content;
    private Long postId;
    private Long adminId;
    //TODO: will model mapper be able to map this?
    private String visitorName;
    private String visitorEmail;
}
