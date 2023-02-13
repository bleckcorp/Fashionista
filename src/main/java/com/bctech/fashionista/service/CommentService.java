package com.bctech.fashionista.service;

import com.bctech.fashionista.dto.request.CommentRequestDto;
import com.bctech.fashionista.dto.response.AdminResponseDto;
import com.bctech.fashionista.dto.response.CommentResponseDto;
import com.bctech.fashionista.dto.response.PaginateResponse;
import com.bctech.fashionista.dto.response.VisitorResponseDto;

public interface CommentService {
    CommentResponseDto createCommentByAdmin(CommentRequestDto commentRequestDto, AdminResponseDto adminResponseDto);


    CommentResponseDto createCommentByVisitor(CommentRequestDto commentRequestDto, VisitorResponseDto visitorResponseDto);

    PaginateResponse<CommentResponseDto> fetchCommentOfAPost(Long postId, int start, int limit);

    PaginateResponse<CommentResponseDto> getAllCommentsOfVisitorByEmail(String email, int start, int limit);

    PaginateResponse<CommentResponseDto> getAllCommentsOfVisitorById(Long id, int start, int limit);

    Boolean deleteComment(Long commentId);
}
