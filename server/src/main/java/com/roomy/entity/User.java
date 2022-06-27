package com.roomy.entity;

import com.roomy.entity.othertype.UserRole;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Builder(toBuilder = true)
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

    private String password;

    private String email;

    @Column(nullable = true)
    private String profile;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder.Default
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Like> likeList = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Board> boardList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "fromUser",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Follow> followingList = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "toUser",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Follow> followerList = new HashSet<>();

    @Override
    public String getId() {
        return username;
    }

    @Override
    public boolean isNew() {
        return createDate == null;
    }


    public void setRoom(Room room) {
        this.room = room;
    }

    public void updateNicknameAndProfile(String nickname, String profile) {
        this.nickname= nickname;
        this.profile = profile;
    }

}
