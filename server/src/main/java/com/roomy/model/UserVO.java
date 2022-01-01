package com.roomy.model;

import com.roomy.model.othertype.Birth;
import com.roomy.model.othertype.UserGender;
import com.roomy.model.othertype.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.*;

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
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Long userSeq;

    @Column(unique = true)
    private String username;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private RoomVO room;

    // 비밀번호
    private String password;

    // 이메일
    private String email;

    // 성별
    @Enumerated(EnumType.STRING)
    private UserGender userGender;

    // 생년월일
    @Embedded
    private Birth birth;

    // 프로필 사진
    @Column(nullable = true)
    private String profile;

    // 회원 닉네임
    private String nickname;

    // 회원 권한 user:role = N : N
    // jpa 에서 다대다 관계 매핑을 할때 1 대 다 와 다대1로 풀어서 매핑을 하는 것이 더 좋다
    @ManyToMany
    /**@Colum(name="") 을 JoinClumn의 의 이름으로 설정을 해줘야된다.
     * tbl_user (1) - tbl_user_role(N) - tbl_role (1) 이런식으로*/
    @JoinTable(
            name = "tbl_user_role",
            joinColumns = {@JoinColumn(name = "user_seq", referencedColumnName = "user_seq")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    private Collection<RoleVO> role = new ArrayList<>();

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
