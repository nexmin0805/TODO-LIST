package TODOLIST.atom.dto.member;

import TODOLIST.atom.domain.Member;
import TODOLIST.atom.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private MemberRole role;

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getUsername(),
                member.getPassword(),
                member.getNickname(),
                member.getEmail(),
                member.getRole());
    }
}
