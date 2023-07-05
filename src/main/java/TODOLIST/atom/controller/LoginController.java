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

// Login
@Controller
@RequestMapping("/login")
public class LoginController {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String moveLogin(){
        return "login/login";
    }

    @PostMapping
    public String checkLogin(@RequestBody MemberRequestDto member) {
        Member checkLogin = loginService.checkLogin(member.getUsername());

        boolean isMatch = passwordEncoder.matches(member.getPassword(), checkLogin.getPassword());
        if (isMatch) return "redirect:/";
        else return "redirect:/login";
    }
}

