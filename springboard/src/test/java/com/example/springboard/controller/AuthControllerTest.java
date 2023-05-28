package com.example.springboard.controller;

import com.example.springboard.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Auth] Controller testing")
@WebMvcTest({ArticleControllerTest.class})
@Import({SecurityConfig.class})
public class AuthControllerTest {
    private MockMvc mvc;

    public AuthControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName("Login page test")
    public void givenNothing_whenRequestingLoginPage_returnLoginPage() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().isOk());
    }
}
