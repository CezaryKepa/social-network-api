package com.speakout.speakoutapi.customer;

import com.speakout.speakoutapi.base_entity.BaseEntity;
import com.speakout.speakoutapi.comment.Comment;
import com.speakout.speakoutapi.post.Post;
import com.speakout.speakoutapi.user.ApplicationUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BaseEntity {
    @OneToOne
    private ApplicationUser applicationUser;
    private String username;
    @OneToMany(mappedBy = "author")
    private List<Post> posts;
    @OneToMany(mappedBy = "author")
    private List<Comment> comments;
}
