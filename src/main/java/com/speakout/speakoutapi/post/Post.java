package com.speakout.speakoutapi.post;


import com.speakout.speakoutapi.base_entity.BaseEntity;
import com.speakout.speakoutapi.comment.Comment;
import com.speakout.speakoutapi.customer.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post extends BaseEntity {
    private String content;
    @ManyToOne
    private Customer author;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
    private Integer likes;
}
