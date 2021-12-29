package com.roomy.model;

import com.roomy.model.othertype.Birth;
import com.roomy.model.othertype.UserGender;
import com.roomy.model.othertype.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name ="tbl_user" , schema = "roomyDB")
public class UserVO {



//    pk를 user_seq 에서 userId로 변경해 필요없어짐
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_seq")
//    //회원번호
//    private Long id;

    // 화원 아이디
    @Id
    private String userId;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private RoomVO room;

    // 비밀번호
    private String userPassword;

    // 이메일
    private String userEmail;

    // 성별
    @Enumerated(EnumType.STRING)
    private UserGender userGender;

    // 생년월일
    @Embedded
    private Birth userBirth;

    // 프로필 사진
    @Column(nullable = true)
    private String userProfile;

    // 회원 이름
    private String userName;

    // 회원 등급 ( ADMIN, USER )
    @Enumerated(EnumType.STRING)
    private UserRole role;

    // 해당 유저가 팔로우한 유저리스트
    @OneToMany(mappedBy = "user")
    private List<FollowVO> followList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    // 해당 유저를 팔로우한 유저리스트
    private List<FollowerVO> followerList = new ArrayList<>();

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>();
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
}
