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
    private Set<Customer> observers;

    @Builder
    public CustomerDto(Long id, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, String username,
                       List<Post> posts, List<Comment> comments, Set<Customer> observers) {
        super(id, createdDate, lastModifiedDate);
        this.username=username;
        this.posts=posts;
        this.comments=comments;
        this.observers=observers;
    }

    public CustomerDto() {
    }
}
