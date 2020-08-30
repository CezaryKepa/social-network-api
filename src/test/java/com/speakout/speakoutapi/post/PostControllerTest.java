package com.speakout.speakoutapi.post;

import com.speakout.speakoutapi.comment.CommentDto;
import com.speakout.speakoutapi.customer.CustomerDto;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    @Mock
    PostService postService;

    @InjectMocks
    PostController controller;


    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void addPost() throws Exception {
        PostDto postDto = PostDto.builder().content("test").build();
        given(postService.save(postDto)).willReturn(postDto);
        mockMvc.perform(post("/api/post/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"content\": \"test\"}" )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void updatePost() throws Exception {
        PostDto postDto = PostDto.builder().content("test").build();
        given(postService.update(postDto)).willReturn(postDto);
        mockMvc.perform(put("/api/post/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"content\": \"test\"}" )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addComment() throws Exception {
        List<CommentDto> comments = new ArrayList<>();
        CommentDto commentDto1 = CommentDto.builder().content("test1").build();
        CommentDto commentDto2 = CommentDto.builder().content("test2").build();
        comments.add(commentDto1);
        comments.add(commentDto2);
        PostDto postDto = PostDto.builder().comments(comments).build();
        given(postService.addComment(commentDto2)).willReturn(postDto);
        mockMvc.perform(put("/api/post/add-comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"content\": \"test2\"}" )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comments").isArray())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void likePost() throws Exception {
        Set<CustomerDto> likes = new HashSet<>();
        CustomerDto customerDto1 = CustomerDto.builder().username("user1").build();
        CustomerDto customerDto2 = CustomerDto.builder().username("user2").build();
        likes.add(customerDto1);
        likes.add(customerDto2);
        PostDto postDto = PostDto.builder().id(1L).likes(likes).build();
        given(postService.likePost(any())).willReturn(postDto);
        mockMvc.perform(put("/api/post/like/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.likes").isArray())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}