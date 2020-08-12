package com.speakout.speakoutapi.comment;

import com.speakout.speakoutapi.base_entity.BaseEntity;
import com.speakout.speakoutapi.post.Post;
import com.speakout.speakoutapi.customer.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment extends BaseEntity {
    @ManyToOne
    private Post post;
    @ManyToOne
    private Customer author;
    private Integer likes;

}
