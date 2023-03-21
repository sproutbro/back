package com.jw.back.controller;

import com.jw.back.auth.AuthController;
import com.jw.back.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private AuthController authController;

    @Autowired
    private AdminController adminController;

    @Test
    @DisplayName("권한 없이 인증필요페이지 접속시 403응답인지 확인")
    void test1() throws Exception {
        mockMvc.perform(get("/api/admin/test1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("권한 없이 인증필요페이지 접속시 403응답인지 확인")
    void test2() throws Exception {
        mockMvc.perform(get("/api/admin/test1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("토큰 달고 갈때 인증필요페이지 접속시 200응답인지 확인")
    void test3() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        ResponseEntity<?> responseEntity = authController.authenticateUser(user);
        HttpHeaders headers = responseEntity.getHeaders();

        mockMvc.perform(get("/api/admin/test1")
                        .header("Authorization", headers.get("Authorization")))
                .andExpect(status().isOk());
    }
}