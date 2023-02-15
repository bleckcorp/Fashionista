package com.bctech.fashionista.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bctech.fashionista.dto.request.CommentRequestDto;
import com.bctech.fashionista.dto.response.ApiResponse;
import com.bctech.fashionista.dto.response.CommentResponseDto;
import com.bctech.fashionista.dto.response.PaginateResponse;
import com.bctech.fashionista.entity.Comment;
import com.bctech.fashionista.entity.Post;
import com.bctech.fashionista.entity.Visitor;
import com.bctech.fashionista.repository.AdminRepository;
import com.bctech.fashionista.repository.CommentRepository;
import com.bctech.fashionista.repository.PostRepository;
import com.bctech.fashionista.repository.VisitorRepository;
import com.bctech.fashionista.service.implementation.CommentServiceImpl;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CommentControllerTest {
    /**
     * Method under test: {@link CommentController#createCommentByVisitor(CommentRequestDto)}
     */
    @Test
    void testCreateCommentByVisitor() {


        CommentRepository commentRepository = mock(CommentRepository.class);
        when(commentRepository.save((Comment) any())).thenReturn(new Comment());
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.findById((Long) any())).thenReturn(Optional.of(new Post()));
        VisitorRepository visitorRepository = mock(VisitorRepository.class);
        when(visitorRepository.findById((Long) any())).thenReturn(Optional.of(new Visitor()));
        CommentController commentController = new CommentController(
                new CommentServiceImpl(commentRepository, postRepository, visitorRepository, mock(AdminRepository.class)));
        ResponseEntity<ApiResponse<CommentResponseDto>> actualCreateCommentByVisitorResult = commentController
                .createCommentByVisitor(new CommentRequestDto("Not all who wander are lost", 123L, 123L));
        assertTrue(actualCreateCommentByVisitorResult.hasBody());
        assertTrue(actualCreateCommentByVisitorResult.getHeaders().isEmpty());
        assertEquals(200, actualCreateCommentByVisitorResult.getStatusCodeValue());
        ApiResponse<CommentResponseDto> body = actualCreateCommentByVisitorResult.getBody();
        assertEquals("SUCCESS", body.getMessage());
        assertEquals(HttpStatus.CREATED, body.getStatus());
        assertNull(body.getError());
        CommentResponseDto data = body.getData();
        assertNull(data.getAuthorType());
        assertNull(data.getContent());
        assertNull(data.getId());
        verify(commentRepository).save((Comment) any());
        verify(postRepository).findById((Long) any());
        verify(visitorRepository).findById((Long) any());
    }


    /**
     * Method under test: {@link CommentController#deleteComment(Long)}
     */


    /**
     * Method under test: {@link CommentController#fetchAllCommentsOfAPost(int, int, Long)}
     */
    @Test
    void testFetchAllCommentsOfAPost() {


        CommentRepository commentRepository = mock(CommentRepository.class);
        ArrayList<Comment> commentList = new ArrayList<>();
        when(commentRepository.findAllByPost((Post) any(), (Pageable) any())).thenReturn(new PageImpl<>(commentList));
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.findById((Long) any())).thenReturn(Optional.of(new Post()));
        ResponseEntity<ApiResponse<PaginateResponse<CommentResponseDto>>> actualFetchAllCommentsOfAPostResult = (new CommentController(
                new CommentServiceImpl(commentRepository, postRepository, mock(VisitorRepository.class),
                        mock(AdminRepository.class)))).fetchAllCommentsOfAPost(1, 1, 123L);
        assertTrue(actualFetchAllCommentsOfAPostResult.hasBody());
        assertTrue(actualFetchAllCommentsOfAPostResult.getHeaders().isEmpty());
        assertEquals(200, actualFetchAllCommentsOfAPostResult.getStatusCodeValue());
        ApiResponse<PaginateResponse<CommentResponseDto>> body = actualFetchAllCommentsOfAPostResult.getBody();
        assertEquals("SUCCESS", body.getMessage());
        assertEquals(HttpStatus.OK, body.getStatus());
        assertNull(body.getError());
        PaginateResponse<CommentResponseDto> data = body.getData();
        assertEquals(commentList, data.getContent());
        assertEquals(0L, data.getTotalElements());
        verify(commentRepository).findAllByPost((Post) any(), (Pageable) any());
        verify(postRepository).findById((Long) any());
    }


    /**
     * Method under test: {@link CommentController#fetchAllCommentsOfAVisitor(int, int, Long)}
     */
    @Test
    void testFetchAllCommentsOfAVisitor() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        CommentRepository commentRepository = mock(CommentRepository.class);
        ArrayList<Comment> commentList = new ArrayList<>();
        when(commentRepository.findAllByVisitor((Visitor) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(commentList));
        VisitorRepository visitorRepository = mock(VisitorRepository.class);
        when(visitorRepository.findById((Long) any())).thenReturn(Optional.of(new Visitor()));
        ResponseEntity<ApiResponse<PaginateResponse<CommentResponseDto>>> actualFetchAllCommentsOfAVisitorResult = (new CommentController(
                new CommentServiceImpl(commentRepository, mock(PostRepository.class), visitorRepository,
                        mock(AdminRepository.class)))).fetchAllCommentsOfAVisitor(1, 1, 123L);
        assertTrue(actualFetchAllCommentsOfAVisitorResult.hasBody());
        assertTrue(actualFetchAllCommentsOfAVisitorResult.getHeaders().isEmpty());
        assertEquals(200, actualFetchAllCommentsOfAVisitorResult.getStatusCodeValue());
        ApiResponse<PaginateResponse<CommentResponseDto>> body = actualFetchAllCommentsOfAVisitorResult.getBody();
        assertEquals("SUCCESS", body.getMessage());
        assertEquals(HttpStatus.OK, body.getStatus());
        assertNull(body.getError());
        PaginateResponse<CommentResponseDto> data = body.getData();
        assertEquals(commentList, data.getContent());
        assertEquals(0L, data.getTotalElements());
        verify(commentRepository).findAllByVisitor((Visitor) any(), (Pageable) any());
        verify(visitorRepository).findById((Long) any());
    }

}

