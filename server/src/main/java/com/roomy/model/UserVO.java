package com.roomy.model;

import com.roomy.model.othertype.Birth;
import com.roomy.model.othertype.Gender;
import com.roomy.model.othertype.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.LAZY;

@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name ="tbl_user" , schema = "roomyDB")
public class UserVO {

    //userId > Security 사용할때
    @Id
    @Column(unique = true)
    private String username;

    // RoomVO user 에 의해 매핑
    @OneToOne(mappedBy = "user", fetch = LAZY)
    private RoomVO room;

    // 비밀번호
    private String password;

    // 이메일
    private String email;

    // 성별
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // 생년월일
    @Embedded
    private Birth birth;

    // 프로필 사진
    @Column(nullable = true)
    private String profile;

    // 회원 닉네임
    private String nickname;

    // 권한
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private List<LikeVO> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private List<GuestVO> guestList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private List<CommentVO> commentList = new ArrayList<>();

    // 해당 유저가 팔로우한 유저리스트
    @OneToMany(mappedBy = "user", fetch = LAZY)
    private List<FollowVO> followList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = LAZY)
    // 해당 유저를 팔로우한 유저리스트
    private List<FollowerVO> followerList = new ArrayList<>();



    // 회원 권한 user:role = N : N
    // jpa 에서 다대다 관계 매핑을 할때 1 대 다 와 다대1로 풀어서 매핑을 하는 것이 더 좋다
    // 왜 ? 중간테이블이 숨겨져 있기 때문에 복잡하고 예상치 못한 쿼리 발생할 수 있음,
    // 그외 추가칼럼을 넣을 수 없다.
//    @ManyToMany
//    //@Colum(name="") 을 JoinClumn의 의 이름으로 설정을 해줘야된다.
//    //tbl_user (1) - tbl_user_role(N) - tbl_role (1) 이런식으로
//    @JoinTable(
//            name = "tbl_user_role",
//            joinColumns = {@JoinColumn(name = "username", referencedColumnName = "username")},
//            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "role_name")})
//    private Collection<RoleVO> role = new ArrayList<>();

}
