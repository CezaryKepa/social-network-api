package com.speakout.speakoutapi.customer;

import com.speakout.speakoutapi.base_entity.BaseItem;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends BaseItem {
    private String username;

    @Builder
    public CustomerDto(Long id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String username) {
        super(id, createdAt, updatedAt);
        this.username = username;
    }
}
