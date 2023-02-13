package com.bctech.fashionista.service.implementation;


import com.bctech.fashionista.dto.request.LikeRequestDto;
import com.bctech.fashionista.dto.response.LikesResponse;
import com.bctech.fashionista.entity.Likes;
import com.bctech.fashionista.exceptions.customexceptions.PostNotFoundException;
import com.bctech.fashionista.exceptions.customexceptions.VisitorNotFoundException;
import com.bctech.fashionista.repository.LikeRepository;
import com.bctech.fashionista.repository.PostRepository;
import com.bctech.fashionista.repository.VisitorRepository;
import com.bctech.fashionista.service.LikeService;
import com.bctech.fashionista.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    private final VisitorRepository visitorRepository;


    @Override
    public LikesResponse likeOrUnlikePost(LikeRequestDto likeRequestDto) {
        LikesResponse likesResponse = new LikesResponse();
        //unlike a post when visitor tries to like a post again
        // check if like exist, if like does not exist : create like
        var like = likeRepository.findByVisitorIdAndPostId(likeRequestDto.getVisitorId(), likeRequestDto.getPostId());

        if (like.isPresent()) {
            likeRepository.delete(like.get());
            likesResponse.setIsLiked(false);
            return likesResponse;
        }
       else{ createLikeForPost(likeRequestDto);
        likesResponse.setIsLiked(true);
        return likesResponse;}
    }

    private void createLikeForPost(LikeRequestDto likeRequestDto) {
        var post = postRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(likeRequestDto.getPostId()));

        var visitor = visitorRepository.findById(likeRequestDto.getVisitorId())
                .orElseThrow(() -> new VisitorNotFoundException(likeRequestDto.getVisitorId()));

        likeRepository.save(Likes.builder()
                .post(post)
                .visitor(visitor)
                .build());
    }




}
