package com.speakout.speakoutapi.comment;

import com.speakout.speakoutapi.base_entity.BaseEntity;
import com.speakout.speakoutapi.post.Post;
import com.speakout.speakoutapi.customer.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment extends BaseEntity {
    @ManyToOne
    private Post post;
    @ManyToOne
    private Customer author;
    private String content;
    @OneToMany(mappedBy = "comment")
    private Set<Customer> likes;

}
