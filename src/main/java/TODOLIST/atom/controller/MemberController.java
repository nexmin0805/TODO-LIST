package TODOLIST.atom.controller;

import TODOLIST.atom.dto.member.MemberEditRequestDto;
import TODOLIST.atom.dto.member.MemberRequestDto;
import TODOLIST.atom.dto.member.MemberResponseDto;
import TODOLIST.atom.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public TODOLIST.atom.response.Response memberSave(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto = memberService.save(memberRequestDto);
        return TODOLIST.atom.response.Response.success(memberResponseDto);
    }

    @PatchMapping("/{id}")
    public TODOLIST.atom.response.Response memberEdit(@PathVariable Long id, @Valid @RequestBody MemberEditRequestDto memberEditRequestDto) {
        MemberResponseDto memberResponseDto = memberService.update(id, memberEditRequestDto);
        return TODOLIST.atom.response.Response.success(memberResponseDto);
    }

    @DeleteMapping("/{id}")
    public TODOLIST.atom.response.Response memberDelete(@PathVariable Long id) {
        memberService.delete(id);
        return TODOLIST.atom.response.Response.success("회원 삭제 완료");
    }
}