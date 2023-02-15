package com.bctech.fashionista.service.implementation;

import com.bctech.fashionista.constants.AuthorType;
import com.bctech.fashionista.dto.request.CommentRequestDto;
import com.bctech.fashionista.dto.response.AdminResponseDto;
import com.bctech.fashionista.dto.response.CommentResponseDto;
import com.bctech.fashionista.dto.response.PaginateResponse;
import com.bctech.fashionista.dto.response.VisitorResponseDto;
import com.bctech.fashionista.entity.Comment;
import com.bctech.fashionista.exceptions.customexceptions.AdminNotFoundException;
import com.bctech.fashionista.exceptions.customexceptions.CommentNotFoundException;
import com.bctech.fashionista.exceptions.customexceptions.PostNotFoundException;
import com.bctech.fashionista.exceptions.customexceptions.VisitorNotFoundException;
import com.bctech.fashionista.repository.AdminRepository;
import com.bctech.fashionista.repository.CommentRepository;
import com.bctech.fashionista.repository.PostRepository;
import com.bctech.fashionista.repository.VisitorRepository;
import com.bctech.fashionista.service.CommentService;
import com.bctech.fashionista.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final VisitorRepository visitorRepository;
    private final AdminRepository adminRepository;


    @Override
    public CommentResponseDto createCommentByAdmin(CommentRequestDto commentRequestDto, AdminResponseDto adminResponseDto) {

        var admin = adminRepository.findById(adminResponseDto.getId())
                .orElseThrow(() -> new AdminNotFoundException(adminResponseDto.getId()));

        var post = postRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentRequestDto.getPostId()));

        return ModelMapperUtils.map(commentRepository.save(Comment.builder()
                .post(post)
                .admin(admin)
                .authorType(AuthorType.ADMIN)
                .content(commentRequestDto.getContent())
                .build()), CommentResponseDto.class);
    }

    @Override
    public CommentResponseDto createCommentByVisitor(CommentRequestDto commentRequestDto){
        var visitor = visitorRepository.findById(commentRequestDto.getVisitorId())
                .orElseThrow(() -> new VisitorNotFoundException(commentRequestDto.getVisitorId()));

        var post = postRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentRequestDto.getPostId()));

        return ModelMapperUtils.map(commentRepository.save(Comment.builder()
                .post(post)
                .visitor(visitor)
                .authorType(AuthorType.VISITOR)
                .content(commentRequestDto.getContent())
                .build()), CommentResponseDto.class);
    }

    @Override
    public PaginateResponse<CommentResponseDto> fetchCommentOfAPost(Long postId,int start, int limit) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        Page<Comment> comments = commentRepository.findAllByPost(post, PageRequest.of(start, limit));
        return  PaginateResponse.<CommentResponseDto>builder()
                .content(ModelMapperUtils.mapAll(comments.getContent(), CommentResponseDto.class))
                .totalElements(comments.getTotalElements())
                .build();
    }
    @Override
    public PaginateResponse<CommentResponseDto> getAllCommentsOfVisitorByEmail( String email, int start, int limit){

        var visitor = visitorRepository.findByEmail(email)
                .orElseThrow(() -> new VisitorNotFoundException(email));

        Page<Comment> comments = commentRepository.findAllByVisitor(visitor, PageRequest.of(start, limit));

        return  PaginateResponse.<CommentResponseDto>builder()
                .content(ModelMapperUtils.mapAll(comments.getContent(), CommentResponseDto.class))
                .totalElements(comments.getTotalElements())
                .build();

    }

    @Override
    public PaginateResponse<CommentResponseDto> getAllCommentsOfVisitorById(Long id, int start, int limit){

        var visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new VisitorNotFoundException(id));

        Page<Comment> comments = commentRepository.findAllByVisitor(visitor, PageRequest.of(start, limit));

        return  PaginateResponse.<CommentResponseDto>builder()
                .content(ModelMapperUtils.mapAll(comments.getContent(), CommentResponseDto.class))
                .totalElements(comments.getTotalElements())
                .build();

    }
    @Override
    public Boolean deleteComment(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        commentRepository.delete(comment);
        return true;

    }



}


