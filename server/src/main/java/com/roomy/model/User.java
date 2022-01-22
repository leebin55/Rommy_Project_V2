package com.roomy.model;

import com.roomy.model.othertype.UserRole;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Builder
@Getter
@Table(name ="tbl_user" , schema = "roomyDB")
public class User extends BaseEntity implements Persistable<String> {

    //userId > Security 사용할때
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
    @Id
    private String username;

    // RoomVO user 에 의해 매핑
    // User 가 등록 될때 Room 도 같이 등록 되기 위해서 두 연관관계 영속성 전이 설정
    @OneToOne(mappedBy = "user", fetch = LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Room room;

    // 비밀번호
    private String password;

    // 이메일
    private String email;


    // 프로필 사진
    @Column(nullable = true)
    private String profile;

    // 회원 닉네임
    private String nickname;

    // 권한
    @Enumerated(EnumType.STRING)
//    @ColumnDefault("ROLE_USER")
    private UserRole role;

    @Builder.Default
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Like> likeList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "fromUser",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Follow> followingList = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "toUser",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Follow> followerList = new HashSet<>();

    @Override    // Persistable 관련
    public String getId() {
        return username;
    }

    @Override // Persistable 관련
    public boolean isNew() {
        return createDate == null;
    }

    // 연관관계로 다른 엔티티에서 참조될때 사용하기 위해
    public void setRoom(Room room) {
        this.room = room;
    }



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
