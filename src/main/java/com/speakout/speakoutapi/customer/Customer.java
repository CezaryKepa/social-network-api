package com.speakout.speakoutapi.customer;

import com.speakout.speakoutapi.base_entity.BaseEntity;
import com.speakout.speakoutapi.comment.Comment;
import com.speakout.speakoutapi.post.Post;
import com.speakout.speakoutapi.user.ApplicationUser;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {
    @OneToOne
    private ApplicationUser applicationUser;
    private String username;
    @OneToMany(mappedBy = "author")
    private List<Post> posts;
    @OneToMany(mappedBy = "author")
    private List<Comment> comments;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_observers",
            joinColumns = @JoinColumn(name = "observer_id"),
            inverseJoinColumns = @JoinColumn(name = "observed_id"))
    private Set<Customer> observed = new HashSet<>();

}
