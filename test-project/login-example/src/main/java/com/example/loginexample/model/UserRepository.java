package com.example.loginexample.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.loginexample.dto.UserReq.UserJoinDto;
import com.example.loginexample.dto.UserReq.UserLoginDto;
import com.example.loginexample.dto.UserReq.UserUpdateReqDto;

@Mapper
public interface UserRepository {
    public List<User> findAll();

    public User findById(int id);

    public User findByUsernameAndPassword(@Param("uDto") UserLoginDto uDto);

    public int insert(@Param("uDto") UserJoinDto uDto);

    public int updateById(@Param("uDto") UserUpdateReqDto uDto);

    public int deleteById(int id);
}
