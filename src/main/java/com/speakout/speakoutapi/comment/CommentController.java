package com.speakout.speakoutapi.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PutMapping("/like/{commentId}")
    public CommentDto likeComment(@PathVariable("commentId") Long commentId){
           return commentService.likeComment(commentId);
    }
}
