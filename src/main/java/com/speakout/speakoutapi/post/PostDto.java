package com.speakout.speakoutapi.post;

import com.speakout.speakoutapi.base_entity.BaseItem;
import com.speakout.speakoutapi.comment.Comment;
import com.speakout.speakoutapi.customer.Customer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PostDto extends BaseItem {
    private String content;
    private Customer author;
    private List<Comment> comments;
    private Integer likes;

    @Builder
    public PostDto(Long id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String content, Customer author, List<Comment> comments, Integer likes) {
        super(id, createdAt, updatedAt);
        this.content = content;
        this.author = author;
        this.comments = comments;
        this.likes = likes;
    }
}
