package com.example.loginexample.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.example.loginexample.model.User;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserControllerTest {
    
    @Autowired
    private MockMvc mvc;

    private MockHttpSession session;

    @BeforeEach
    public void setUp(){
        User mockUser = new User();
        mockUser.setId(2);
        mockUser.setUsername("ssar");
        mockUser.setPassword("1234");
        mockUser.setEmail("ssar@nate.com");

        session = new MockHttpSession();
        session.setAttribute("principal", mockUser);
    }

    @Transactional
    @Test
    public void userJoin_test() throws Exception{
        // String resp = "username=ssar&password=1234&email=ss321@nate.com"; 
        // Body = <script>alert('동일한 username이 존재합니다');history.back();</script>
        String resp = "username=ssar123&password=1234&email=ss321@nate.com"; 

        ResultActions rs = mvc.perform(post("/join").content(resp).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        rs.andExpect(status().is3xxRedirection());
    }

    @Transactional
    @Test
    public void userLogin_test() throws Exception{
        //given
        String resp = "username=ssar&password=1234";
        // Body = <script>alert('아이디를 입력하세요');history.back();</script>
        // Body = <script>alert('패스워드를 입력하세요');history.back();</script>
        // Body = <script>alert('아이디 또는 비밀번호가 틀렸습니다.');history.back();</script>

        //when
        ResultActions rs = mvc.perform(post("/login").content(resp).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    
        //then
        MvcResult mvcResult = rs.andExpect(status().is3xxRedirection()).andReturn();
        HttpSession session =  mvcResult.getRequest().getSession();
        User userPS = (User) session.getAttribute("principal");
        assertThat(userPS.getUsername()).isEqualTo("ssar");
        assertThat(userPS.getPassword()).isEqualTo("1234");
    }

    @Transactional
    @Test
    public void main_test() throws Exception {
        ResultActions rs = mvc.perform(get("/").session(session));
        MvcResult mvc =  rs.andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getForwardedUrl();
        assertThat(result).isEqualTo("/WEB-INF/view/user/main.jsp");
    }
}
