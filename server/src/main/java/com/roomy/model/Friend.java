package com.roomy.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "tbl_follow" ,schema="roomyDB")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long friendId;

    // 회원 아이디
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

    //  팔로우(친구맺기)를 한 회원 아이디
    private String followUser;

//    public void setUser(UserVO user){
//        this.user = user;
//        user.getFollowList().add(this);
//    }
    private String followerUser;

    //연관관계 메서드
    public void setUser(User user){
        this.user = user;
        user.setFriend(this);
    }
}
