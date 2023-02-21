package com.example.loginexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.loginexample.dto.UserReq.UserJoinReqDto;
import com.example.loginexample.dto.UserReq.UserLoginReqDto;
import com.example.loginexample.exception.CustomException;
import com.example.loginexample.model.User;
import com.example.loginexample.model.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(UserJoinReqDto userDto){
        User sameUser = userRepository.findByUsername(userDto.getUsername());
        if (sameUser != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }
        try {
            userRepository.insert(userDto);        
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException("회원가입에 실패 했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);   
        }
    }

    @Transactional
    public User 로그인(UserLoginReqDto userDto){
        User principal = userRepository.findByUsernameAndPassword(userDto);
        if ( principal == null ){
            throw new CustomException("아이디 또는 비밀번호가 틀렸습니다.");  
        }
        return principal;
    }
        
}
