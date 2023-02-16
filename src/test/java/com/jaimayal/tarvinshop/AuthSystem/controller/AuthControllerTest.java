package com.jaimayal.tarvinshop.AuthSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaimayal.tarvinshop.AuthSystem.dto.TokenRefreshDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.TokenResponseDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.UserLoginDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.UserRegisterDTO;
import com.jaimayal.tarvinshop.AuthSystem.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AuthService authService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach
    public void setup() {
        TokenResponseDTO response = new TokenResponseDTO(
                "woquheiuqw.asdijaiodquifheiughruiefg.asudhbyuigbyu1qfni",
                "Bearer",
                3600L
        );
        given(authService.register(any())).willReturn(response);
        given(authService.refresh(any())).willReturn(response);
        given(authService.login(any())).willReturn(response);
    }

    @Test
    void login() throws Exception {
        UserLoginDTO dto = new UserLoginDTO("jaimayal@gmail.com", "12345678");
        mockMvc.perform(MockMvcRequestBuilders
                            .post("/oauth/login")
                            .content(objectMapper.writeValueAsString(dto))
                            .contentType(MediaType.APPLICATION_JSON)
                            .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.token").value("woquheiuqw.asdijaiodquifheiughruiefg.asudhbyuigbyu1qfni"),
                        jsonPath("$.tokenType").value("Bearer"),
                        jsonPath("$.expiresIn").value(3600L)
                );
    }

    @Test
    void register() throws Exception {
        UserRegisterDTO dto = new UserRegisterDTO("Jaime", "Ayala", "jaimayal@gmail.com", "12345678");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/oauth/register")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.token").value("woquheiuqw.asdijaiodquifheiughruiefg.asudhbyuigbyu1qfni"),
                        jsonPath("$.tokenType").value("Bearer"),
                        jsonPath("$.expiresIn").value(3600L)
                );
    }

    @Test
    void refresh() throws Exception {
        TokenRefreshDTO dto = new TokenRefreshDTO();
        dto.setToken("asdasdasd.asdasdq23123as.qwe123");
        System.out.println(objectMapper.writeValueAsString(dto));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/oauth/refresh")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.token").value("woquheiuqw.asdijaiodquifheiughruiefg.asudhbyuigbyu1qfni"),
                        jsonPath("$.tokenType").value("Bearer"),
                        jsonPath("$.expiresIn").value(3600L)
                );
    }
}