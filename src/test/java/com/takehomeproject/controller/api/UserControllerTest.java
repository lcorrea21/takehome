package com.takehomeproject.controller.api;

import com.takehomeproject.dao.JwtUserDetailsDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

  private String token;

  @Autowired private JwtUserDetailsDAO userDetailsService;

  @Autowired private MockMvc mvc;

  @BeforeAll
  void generateToken() throws Exception {
    String username = "k4nb4n";
    String password = "k4Nb4nD3v#";

    String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

    MvcResult result =
        mvc.perform(
                MockMvcRequestBuilders.post("/authenticate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
            .andExpect(status().isOk())
            .andReturn();

    String response = result.getResponse().getContentAsString();
    response = response.replace("{\"token\":\"", "");
    token = response.replace("\"}", "");

    assertNotNull(token);
  }

  @Test
  void listUsers() throws Exception {

    mvc.perform(
            MockMvcRequestBuilders.get("/api/v1/takehomeproject/users/ListUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void isUserExists() throws Exception {

    String email = "luis@gmail.com";

    mvc.perform(
            MockMvcRequestBuilders.get("/api/v1/takehomeproject/users/isUserExists/{email}", email)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void upsertUser() throws Exception {

    String body = "{\"id\":14,\"firstname\":\"Luis Alfonso\",\"lastname\":\"Correa\",\"email\":\"luis@gmail.com\",\"password\":\"\"}";

    mvc.perform(
            MockMvcRequestBuilders.put("/api/v1/takehomeproject/users/UpsertUser")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body)
                    .header("Authorization", "Bearer " + token))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
