package com.speakout.speakoutapi.comment;

import com.speakout.speakoutapi.base_entity.BaseItem;
import com.speakout.speakoutapi.customer.CustomerDto;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CommentDto extends BaseItem {
    private Long postId;
    private String content;
    private Long authorId;
    private Set<CustomerDto> likes;

    @Builder
    public CommentDto(Long id, OffsetDateTime createdAt, OffsetDateTime updatedAt, Long postId, String content, Long authorId, Set<CustomerDto> likes) {
        super(id, createdAt, updatedAt);
        this.postId = postId;
        this.content = content;
        this.authorId = authorId;
        this.likes = likes;
    }
}
