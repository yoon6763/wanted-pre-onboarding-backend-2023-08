package com.wanted.preonboarding.controller;

import com.wanted.preonboarding.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SignControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("회원가입 성공")
    void signUpSuccess() throws Exception {
        // Given
        String email = "testuser@example.com14";
        String password = "password2";

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/sign-api/sign-up")
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 - email 중복")
    void signUpFailEmail() throws Exception {
        // Given
        String email = "testuser@example.com13";
        String password = "password2";

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/sign-api/sign-up")
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().isOk());

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/sign-api/sign-up")
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}