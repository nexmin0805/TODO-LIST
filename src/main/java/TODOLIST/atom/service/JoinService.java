package TODOLIST.atom.service;

import TODOLIST.atom.domain.Member;
import TODOLIST.atom.dto.member.MemberRequestDto;
import TODOLIST.atom.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final MemberRepository memberRepository;

    public JoinService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void joinMember(MemberRequestDto memberRequestDto) {
        // 회원가입 시 이미 존재하는 사용자인지 검증 필요
        memberRepository.findByUsername(memberRequestDto.getUsername()).ifPresent(m -> {
            throw new IllegalStateException("User already exists with username : " + m.getUsername());
        });

        Member member = new Member(
                memberRequestDto.getUsername(),
                memberRequestDto.getPassword(),
                memberRequestDto.getNickname(),
                memberRequestDto.getEmail(),
                memberRequestDto.getRole()
        );

        memberRepository.save(member);
    }
}