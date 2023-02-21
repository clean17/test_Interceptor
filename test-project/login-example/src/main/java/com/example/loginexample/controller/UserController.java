package com.example.loginexample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginexample.Util.Sha256;
import com.example.loginexample.dto.UserReq.UserJoinReqDto;
import com.example.loginexample.dto.UserReq.UserLoginReqDto;
import com.example.loginexample.exception.CustomException;
import com.example.loginexample.model.User;
import com.example.loginexample.service.UserService;

@Controller
public class UserController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService service;

    // @Autowired
    // private UserRepository userRepository;

    @GetMapping("/")
    public String main() {

        return "user/main";
    }

    @GetMapping("/update")
    public String updateForm() {

        return "user/updateForm";
    }

    @GetMapping("/login")
    public String loginForm() {

        return "user/loginForm";
    }

    @GetMapping("/join")
    public String joinForm() {

        return "user/joinForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/join")
    public String userJoin(UserJoinReqDto userDto) {
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            throw new CustomException("아이디를 입력하세요");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new CustomException("패스워드를 입력하세요");
        }
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            throw new CustomException("이메일을 입력하세요");
        }
        service.회원가입(userDto);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String userLogin(UserLoginReqDto userDto) {
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            throw new CustomException("아이디를 입력하세요");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new CustomException("패스워드를 입력하세요");
        }
        User prinipal = service.로그인(userDto);
        session.setAttribute("principal", prinipal);
        return "redirect:/";
    }

    @GetMapping("/t")
    @ResponseBody
    public String tte() {

        return "z ";
    }

}