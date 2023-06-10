package com.project.classteacher.infra.http.controller.auth;

import com.project.classteacher.config.MyIntegrationConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthControllerTest extends MyIntegrationConfig {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return 200 when login")
    void shouldReturn200WhenLogin() throws Exception {

        mockMvc.perform(post("/auth/login")
                        .header("Authorization", "")
                )
                .andExpect(status().isOk());
    }

}
