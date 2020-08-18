package com.speakout.speakoutapi.customer;

import com.speakout.speakoutapi.base_entity.BaseItem;
import com.speakout.speakoutapi.comment.Comment;
import com.speakout.speakoutapi.post.Post;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends BaseItem {
    private String username;
    private List<Post> posts;
    private List<Comment> comments;
    private Set<CustomerDto> observers;

    @Builder
    public CustomerDto(Long id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String username,
                       List<Post> posts, List<Comment> comments, Set<CustomerDto> observers) {
        super(id, createdAt, updatedAt);
        this.username=username;
        this.posts=posts;
        this.comments=comments;
        this.observers=observers;
    }

}
