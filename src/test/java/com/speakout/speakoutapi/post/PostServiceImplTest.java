package com.speakout.speakoutapi.post;

import com.speakout.speakoutapi.comment.Comment;
import com.speakout.speakoutapi.comment.CommentDto;
import com.speakout.speakoutapi.comment.CommentService;
import com.speakout.speakoutapi.customer.Customer;
import com.speakout.speakoutapi.customer.CustomerDto;
import com.speakout.speakoutapi.customer.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)

class PostServiceImplTest {
    @Mock
    PostRepository postRepository;
    @Mock
    CommentService commentService;
    @Spy
    PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @InjectMocks
    PostServiceImpl postService;

    @Test
    void save() {
        //given
        PostDto postDto = PostDto.builder().content("test").build();
        Post post = new Post();
        post.setContent("test");
        given(postRepository.save(post)).willReturn(post);

        //when
        PostDto saved = postService.save(postDto);

        //then
        then(postRepository).should().save(post);

        assertThat(saved).isEqualTo(postDto);
    }

    @Test
    void update() {
        //given
        PostDto postDto = PostDto.builder().id(1L).content("test").build();
        Post post = new Post();
        post.setContent("test");
        post.setId(1L);
        given(postRepository.findById(postDto.getId())).willReturn(Optional.of(post));
        given(postRepository.save(post)).willReturn(post);

        //when
        PostDto saved = postService.update(postDto);

        //then
        then(postRepository).should().save(post);

        assertThat(saved).isEqualTo(postDto);
    }

    @Test
    void addComment() {
        //given
        CommentDto commentDto = CommentDto.builder().postId(1L).content("test").build();
        Post post = new Post();
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());
        post.setId(1L);
        post.setComments(comments);
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("test");
        given(postRepository.findById(commentDto.getPostId())).willReturn(Optional.of(post));
        given(commentService.save(commentDto)).willReturn(comment);

        //when
        PostDto added = postService.addComment(commentDto);

        //then
        then(postRepository).should().findById(commentDto.getPostId());
        then(commentService).should().save(commentDto);
        post.getComments().add(comment);
        assertThat(added).isEqualTo(postMapper.postToPostDto(post));
    }
}