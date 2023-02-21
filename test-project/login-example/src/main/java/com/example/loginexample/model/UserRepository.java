package com.example.loginexample.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.loginexample.dto.UserReq.UserJoinReqDto;
import com.example.loginexample.dto.UserReq.UserLoginReqDto;
import com.example.loginexample.dto.UserReq.UserUpdateReqDto;

@Mapper
public interface UserRepository {
    public List<User> findAll();

    public User findById(int id);

    public User findByUsernameAndPassword(@Param("uDto") UserLoginReqDto uDto);

    public User findByUsername(String username);

    public int insert(@Param("uDto") UserJoinReqDto uDto);

    public int updateById(@Param("uDto") UserUpdateReqDto uDto);

    public int deleteById(int id);
}
