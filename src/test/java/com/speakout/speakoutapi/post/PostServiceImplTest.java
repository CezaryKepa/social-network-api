package com.speakout.speakoutapi.post;

import com.speakout.speakoutapi.comment.Comment;
import com.speakout.speakoutapi.comment.CommentDto;
import com.speakout.speakoutapi.comment.CommentService;
import com.speakout.speakoutapi.customer.Customer;
import com.speakout.speakoutapi.customer.CustomerDto;
import com.speakout.speakoutapi.customer.CustomerMapper;
import com.speakout.speakoutapi.customer.CustomerService;
import com.speakout.speakoutapi.user.ApplicationUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

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
    @Mock
    CustomerService customerService;
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
        given(postRepository.findById(postDto.getId())).willReturn(Optional.of(post));
        given(postRepository.save(post)).willReturn(post);

        //when
        PostDto saved = postService.update(postDto);

        //then
        then(postRepository).should().findById(postDto.getId());
        then(postRepository).should().save(post);

        assertThat(saved.getContent()).isEqualTo(postDto.getContent());
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
        assertThat(added).isEqualTo(postMapper.postToPostDto(post));
    }

    @Test
    void likePost() {
        //given
        Post post = new Post();
        Set<Customer> likes = new HashSet<>();
        likes.add(new Customer());
        likes.add(new Customer());
        post.setId(1L);
        post.setLikes(likes);
        Customer authCustomer = Customer.builder().username("AuthenticatedCustomer").build();
        given(postRepository.findById(1L)).willReturn(Optional.of(post));
        given(customerService.getAuthenticatedCustomer()).willReturn(authCustomer);

        //when
        PostDto likedPost = postService.likePost(1L);

        //then
        then(postRepository).should().findById(1L);
        then(customerService).should().getAuthenticatedCustomer();
        post.getLikes().add(authCustomer);
        assertThat(likedPost).isEqualTo(postMapper.postToPostDto(post));

    }
}