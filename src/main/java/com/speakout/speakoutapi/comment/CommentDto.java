package com.speakout.speakoutapi.comment;

import com.speakout.speakoutapi.base_entity.BaseItem;
import lombok.*;

import java.time.OffsetDateTime;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CommentDto extends BaseItem {
    private Long postId;
    private String content;
    private Long authorId;
    private Integer likes;

    @Builder
    public CommentDto(Long id, OffsetDateTime createdAt, OffsetDateTime updatedAt, Long postId, String content, Long authorId, Integer likes) {
        super(id, createdAt, updatedAt);
        this.postId = postId;
        this.content = content;
        this.authorId = authorId;
        this.likes = likes;
    }
}
