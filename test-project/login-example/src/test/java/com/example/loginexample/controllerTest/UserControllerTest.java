package com.example.loginexample.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void userJoin_test() throws Exception{
        String resp = "username=df&password=43&email=23@13";

        ResultActions rs = mvc.perform(post("/join").content(resp).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        rs.andExpect(status().is3xxRedirection());
    }

    @Test
    public void userLogin_test() throws Exception{
        String resp = "username=ssar&password=1234";

        ResultActions rs = mvc.perform(post("/login").content(resp).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        rs.andExpect(status().is3xxRedirection());
    }
}
