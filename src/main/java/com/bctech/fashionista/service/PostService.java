package com.bctech.fashionista.service;

import com.bctech.fashionista.dto.request.CategoryDto;
import com.bctech.fashionista.dto.request.PostRequestDTO;
import com.bctech.fashionista.dto.response.PaginateResponse;
import com.bctech.fashionista.dto.response.PostResponseDto;

public interface PostService {

    PostResponseDto createPost(PostRequestDTO postRequestDto);

    PaginateResponse<PostResponseDto> getAllPosts(int start, int limit);

    PaginateResponse<PostResponseDto> getAllPostsByCategory(CategoryDto categoryDto, int start, int limit);

    PostResponseDto updatePost(PostRequestDTO postRequestDTO, Long id);

    Boolean deletePost(Long id);

    Long getAuthorOfPost(Long id);
}
