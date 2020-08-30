package com.speakout.speakoutapi.post;


import com.speakout.speakoutapi.comment.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {
    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addPost(@RequestBody PostDto postDto){
        return postService.save(postDto);
    }
    @PutMapping("/update")
    public PostDto updatePost(@RequestBody PostDto postDto){
        return postService.update(postDto);
    }
    @PutMapping("/add-comment")
    public PostDto addComment(@RequestBody CommentDto commentDto){
        return postService.addComment(commentDto);
    }
    @PutMapping("/like/{postId}")
    public PostDto addComment(@PathVariable("postId") Long postId){
        return postService.likePost(postId);
    }
}
