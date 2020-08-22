package com.speakout.speakoutapi.post;

import com.speakout.speakoutapi.comment.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
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
        //TODO fix and refactor
//        Post commentPost = commentDto.getPost();
//        Optional<Post> postById = postRepository.findById(commentPost.getId());
//        Post post = postById.orElseThrow(PostNotFoundException::new);
//        post.getComments().add(post);
        return null;
    }

    private PostDto mapAndSavePost(PostDto post) {
        Post postEntity = postMapper.postDtoToPost(post);
        Post savedPost = postRepository.save(postEntity);
        return postMapper.postToPostDto(savedPost);
    }
}
