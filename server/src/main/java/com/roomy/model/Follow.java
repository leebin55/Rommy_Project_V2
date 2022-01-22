package com.roomy.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "tbl_follow" ,schema="roomyDB")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "to_User")
    private User toUser;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "from_user")
    private User fromUser;

// ------------------생성자 관련-----------------------
    public static Follow friendEntity(User follower, User following){
        return new Follow(follower, following);
    }

    private Follow(User follower, User following) {
        this.fromUser = follower;
        this.toUser = following;
    }

    //------------ 연관관계 method ------------------
    public void userFollow(Follow follow){
        this.fromUser = follow.fromUser;
        this.toUser = follow.toUser;
        fromUser.getFollowerList().add(this);
        toUser.getFollowingList().add(this);
    }

    public void userUnFollow(User fromUser, User toUser){
        this.fromUser = fromUser;
        this.toUser = toUser;
        fromUser.getFollowerList().remove(this);
        toUser.getFollowingList().remove(this);
    }

}
