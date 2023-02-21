package com.example.loginexample.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.loginexample.dto.UserReq.UserJoinReqDto;
import com.example.loginexample.dto.UserReq.UserLoginReqDto;
import com.example.loginexample.model.User;
import com.example.loginexample.model.UserRepository;

@MybatisTest
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    public void insert_test() throws Exception {
        UserJoinReqDto uDto = new UserJoinReqDto("ssar123","12341234","ssar123@nate.com");
        
        int result = userRepository.insert(uDto);
        assertThat(result).isEqualTo(1);      
    }
    @Transactional
    @Test
    public void findByUsernameAndPassword_test() throws Exception {
        UserLoginReqDto uDto = new UserLoginReqDto("ssar","1234");
        
        User userPS = userRepository.findByUsernameAndPassword(uDto);
        assertThat(userPS.getUsername()).isEqualTo("ssar");
        assertThat(userPS.getPassword()).isEqualTo("1234");
        assertThat(userPS.getEmail()).isEqualTo("ssar@nate.com");
    }
}
