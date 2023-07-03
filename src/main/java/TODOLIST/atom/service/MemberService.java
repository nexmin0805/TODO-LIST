package TODOLIST.atom.service;

import TODOLIST.atom.domain.Member;
import TODOLIST.atom.dto.member.MemberEditRequestDto;
import TODOLIST.atom.dto.member.MemberRequestDto;
import TODOLIST.atom.dto.member.MemberResponseDto;
import TODOLIST.atom.exception.BoardNotFoundException;
import TODOLIST.atom.exception.CommentNotFoundException;
import TODOLIST.atom.exception.MemberNotFoundException;
import TODOLIST.atom.exception.WriteFailureException;
import TODOLIST.atom.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(MemberRequestDto memberRequestDto) {
        Member member = memberRepository.save(new Member(memberRequestDto));
        return MemberResponseDto.toDto(member);
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberEditRequestDto memberEditRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        member.updateMember(memberEditRequestDto);
        return MemberResponseDto.toDto(member);
    }

    public MemberResponseDto findByNickname(String nickName) {
        Member member = memberRepository.findByNickname(nickName).orElseThrow(MemberNotFoundException::new);
        log.info("member = {} ",member.getEmail());
        return MemberResponseDto.toDto(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        memberRepository.delete(member);
    }
}