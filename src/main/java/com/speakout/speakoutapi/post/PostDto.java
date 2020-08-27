package com.speakout.speakoutapi.post;

import com.speakout.speakoutapi.base_entity.BaseItem;
import com.speakout.speakoutapi.customer.CustomerDto;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PostDto extends BaseItem {
    private String content;
    private Long authorId;
    private Set<CustomerDto> likes;


    @Builder
    public PostDto(Long id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String content, Long authorId, Set<CustomerDto> likes) {
        super(id, createdAt, updatedAt);
        this.content = content;
        this.authorId = authorId;
        this.likes = likes;
    }
}
