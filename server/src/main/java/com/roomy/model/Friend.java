package com.roomy.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "tbl_follow" ,schema="roomyDB")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long friendId;
    /**
     * 유저 A 가 B 를 Follow 하면
     * following : B
     * follower : A
     * A 기준으로 조회 할 때는 내가 팔로우한 친구 : where follower = A (B 입장에서는 follwer 가 추가된것)
     * 나를 팔로우한 친구 : where following = A
     */
// User 와 조인한 이유 : 친구 게시판에서 팔로우 팔로워 회원 정보를 보여주기 위해
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn
    private User following;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn
    private User follower;

// ------------------생성자 관련-----------------------
    public static Friend createFriend(User follower, User following){
        return new Friend(follower, following);
    }

    private Friend(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

    //------------ 연관관계 method ------------------
    public void setUser(User following,User follower){
        this.follower = follower;
        this.following = following;
        follower.getFollowerList().add(this);
        following.getFollowingList().add(this);
    }


}
