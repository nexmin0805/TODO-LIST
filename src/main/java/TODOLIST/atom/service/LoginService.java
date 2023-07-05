package TODOLIST.atom.service;

import TODOLIST.atom.domain.Member;
import TODOLIST.atom.repository.MemberRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member checkLogin(String username) {
        // 조회 시 해당 사용자가 없을 경우 예외 처리 필요
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
    }
}