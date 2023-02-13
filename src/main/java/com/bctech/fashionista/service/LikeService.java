package com.bctech.fashionista.service;

import com.bctech.fashionista.dto.request.LikeRequestDto;
import com.bctech.fashionista.dto.response.LikesResponse;
import com.bctech.fashionista.entity.Likes;

import java.util.List;

public interface LikeService {

    LikesResponse likeOrUnlikePost(LikeRequestDto likeRequestDto);

}
