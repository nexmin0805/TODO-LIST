package TODOLIST.atom.controller;

import TODOLIST.atom.domain.Member;
import TODOLIST.atom.dto.member.MemberRequestDto;
import TODOLIST.atom.service.JoinService;
import TODOLIST.atom.service.LoginService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// Join
@Controller
@RequestMapping("/join")
public class JoinController {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping
    public String moveJoin(){
        return "join/join";
    }

    @PostMapping
    public String joinMember(@RequestBody MemberRequestDto member){

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        joinService.joinMember(member);
        return "redirect:/";
    }
}
