package com.takehomeproject.controller.api;

import com.takehomeproject.dao.JwtUserDetailsDAO;
import com.takehomeproject.model.JwtRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationControllerTest {

    @Autowired
    private JwtUserDetailsDAO userDetailsService;

    @Autowired
    private MockMvc mvc;

    @Test
    void createAuthenticationToken() throws Exception {
        String username = "k4nb4n";
        String password = "k4Nb4nD3v#";

        String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";


        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                        .andExpect(status().isOk()).andReturn();
    }
}