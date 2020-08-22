package com.speakout.speakoutapi.post;

import com.speakout.speakoutapi.comment.CommentDto;

public interface PostService {
    PostDto save(PostDto postDto);
    PostDto update(PostDto postDto);
    PostDto addComment(CommentDto commentDto);
}
