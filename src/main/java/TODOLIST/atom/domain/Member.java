package TODOLIST.atom.domain;

import TODOLIST.atom.domain.Board;
import TODOLIST.atom.domain.MemberRole;
import TODOLIST.atom.domain.base.BaseEntity;
import TODOLIST.atom.dto.member.MemberEditRequestDto;
import TODOLIST.atom.dto.member.MemberRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Board> boards = new ArrayList<>();

    public Member(MemberRequestDto memberRequestDto) {
        this.username = memberRequestDto.getUsername();
        this.password = memberRequestDto.getPassword();
        this.nickname = memberRequestDto.getNickname();
        this.email = memberRequestDto.getEmail();
        this.role = memberRequestDto.getRole();
    }

    public Member(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = MemberRole.USER;
    }

    public void updateMember(MemberEditRequestDto memberEditRequestDto) {
        this.password = memberEditRequestDto.getPassword();
        this.email = memberEditRequestDto.getEmail();
        this.nickname = memberEditRequestDto.getNickname();
    }

}
