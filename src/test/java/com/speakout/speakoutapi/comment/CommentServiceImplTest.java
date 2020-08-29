package com.speakout.speakoutapi.comment;

import com.speakout.speakoutapi.customer.Customer;
import com.speakout.speakoutapi.customer.CustomerService;
import com.speakout.speakoutapi.post.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    CommentRepository commentRepository;
    @Mock
    CustomerService customerService;
    @Spy
    CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);

    @InjectMocks
    CommentServiceImpl commentService;


    @Test
    void save() {
        //given
        CommentDto commentDto = CommentDto.builder().content("test").build();
        Comment comment = Comment.builder().content("test").build();
        given(commentRepository.save(comment)).willReturn(comment);

        //when
        Comment saved = commentService.save(commentDto);

        //then
        then(commentRepository).should().save(comment);
        assertThat(commentMapper.commentToCommentDto(saved)).isEqualTo(commentDto);
    }

    @Test
    void likeComment() {
        //given
        Comment comment = new Comment();
        Set<Customer> likes = new HashSet<>();
        likes.add(new Customer());
        likes.add(new Customer());
        comment.setId(1L);
        comment.setLikes(likes);
        Customer authCustomer = Customer.builder().username("AuthenticatedCustomer").build();
        given(commentRepository.findById(1L)).willReturn(Optional.of(comment));
        given(customerService.getAuthenticatedCustomer()).willReturn(authCustomer);

        //when
        CommentDto likedComment= commentService.likeComment(1L);

        //then
        then(commentRepository).should().findById(1L);
        then(customerService).should().getAuthenticatedCustomer();
        comment.getLikes().add(authCustomer);
        assertThat(likedComment).isEqualTo(commentMapper.commentToCommentDto(comment));
    }
}