package com.speakout.speakoutapi.post;

import com.speakout.speakoutapi.comment.Comment;
import com.speakout.speakoutapi.comment.CommentDto;
import com.speakout.speakoutapi.comment.CommentService;
import com.speakout.speakoutapi.customer.Customer;
import com.speakout.speakoutapi.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CommentService commentService;
    private final CustomerService customerService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           PostMapper postMapper,
                           CommentService commentService,
                           CustomerService customerService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.commentService = commentService;
        this.customerService = customerService;
    }

    @Override
    public PostDto save(PostDto postDto) {
        return mapAndSavePost(postDto);
    }

    @Override
    public PostDto update(PostDto postDto) {
        Optional<Post> postById = postRepository.findById(postDto.getId());
        if(postById.isEmpty())
            throw new PostNotFoundException();
        return mapAndSavePost(postDto);
    }

    @Override
    public PostDto addComment(CommentDto commentDto) {
        Optional<Post> postById = postRepository.findById(commentDto.getPostId());
        Post post = postById.orElseThrow(PostNotFoundException::new);
        Comment savedComment = commentService.save(commentDto);
        post.getComments().add(savedComment);
        postRepository.save(post);
        return postMapper.postToPostDto(post);
    }

    @Override
    public PostDto likePost(Long postId) {
        Optional<Post> postById = postRepository.findById(postId);
        Post post = postById.orElseThrow(PostNotFoundException::new);
        Customer authenticatedCustomer = customerService.getAuthenticatedCustomer();
        post.getLikes().add(authenticatedCustomer);
        postRepository.save(post);
        return postMapper.postToPostDto(post);
    }

    private PostDto mapAndSavePost(PostDto post) {
        Post postEntity = postMapper.postDtoToPost(post);
        Post savedPost = postRepository.save(postEntity);
        return postMapper.postToPostDto(savedPost);
    }
}
