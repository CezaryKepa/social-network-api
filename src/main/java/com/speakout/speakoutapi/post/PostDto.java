package com.speakout.speakoutapi.post;

import com.speakout.speakoutapi.base_entity.BaseItem;
import lombok.*;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PostDto extends BaseItem {
    private String content;
    private Long authorId;
    private Integer likes;

    @Builder
    public PostDto(Long id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String content, Long authorId, Integer likes) {
        super(id, createdAt, updatedAt);
        this.content = content;
        this.authorId = authorId;
        this.likes = likes;
    }
}
