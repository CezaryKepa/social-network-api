package com.speakout.speakoutapi.comment;

import com.speakout.speakoutapi.base_entity.BaseItem;
import com.speakout.speakoutapi.customer.Customer;
import com.speakout.speakoutapi.post.Post;
import lombok.*;

import java.time.OffsetDateTime;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CommentDto extends BaseItem {
    private Post post;
    private Customer author;
    private Integer likes;

    @Builder
    public CommentDto(Long id, OffsetDateTime createdAt, OffsetDateTime updatedAt, Post post, Customer author, Integer likes) {
        super(id, createdAt, updatedAt);
        this.post = post;
        this.author = author;
        this.likes = likes;
    }
}
