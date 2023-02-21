package com.example.loginexample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserReq {
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserJoinDto{
        private String username;
        private String password;
        private String email;
    }
    @Getter
    @Setter
    public static class UserLoginDto{
        private String username;
        private String password;
    }
    
    @Getter
    @Setter
    public static class UserUpdateReqDto{
    	private Integer id;
        private String password;
        private String email;
    }
}