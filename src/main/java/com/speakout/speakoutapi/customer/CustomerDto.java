package com.speakout.speakoutapi.customer;

import com.speakout.speakoutapi.base_entity.BaseItem;
import lombok.*;

import java.time.OffsetDateTime;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CustomerDto extends BaseItem {
    private String username;
    private Long postId;
    @Builder
    public CustomerDto(Long id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String username, Long postId) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.postId = postId;
    }
}
