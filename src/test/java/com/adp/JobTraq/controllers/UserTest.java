package com.adp.JobTraq.controllers;

import com.adp.JobTraq.models.UserModel;
import com.adp.JobTraq.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserModel user;

    private static final String BASE_URL = "/api/users";
    private static final String USER_JSON = "{\"name\":\"React Raiders\",\"email\":\"reactraiders@adp.com\",\"password\":\"reactraider123\",\"role\":\"USER\"}";

    @BeforeEach
    void setUp() {
        user = new UserModel();
        user.setId("4250");
        user.setName("React Raiders");
        user.setEmail("reactraiders@adp.com");
        user.setPassword("reactraider123");
        user.setRole("USER");
    }

    @Test
    void createUser_shouldReturnCreatedUser() throws Exception {
        Mockito.when(userService.createUser(any(UserModel.class))).thenReturn(user);

        mockMvc.perform(post(BASE_URL + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(USER_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("React Raiders"))
                .andExpect(jsonPath("$.email").value("reactraiders@adp.com"));
    }

    @Test
    void createUser_shouldReturnInternalServerErrorOnException() throws Exception {
        Mockito.when(userService.createUser(any(UserModel.class))).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(post(BASE_URL + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(USER_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$").value("Failed to create user"));
    }

    @Test
    void updateUser_shouldReturnUpdatedUser() throws Exception {
        Mockito.when(userService.updateUser(anyString(), any(UserModel.class))).thenReturn(user);

        mockMvc.perform(put(BASE_URL + "/update/4250")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(USER_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("React Raiders"))
                .andExpect(jsonPath("$.email").value("reactraiders@adp.com"));
    }

    @Test
    void deleteUser_shouldReturnOk() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/delete/4250"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("User deleted successfully"));
    }

    @Test
    void getUser_shouldReturnUser() throws Exception {
        Mockito.when(userService.findById(anyString())).thenReturn(user);

        mockMvc.perform(get(BASE_URL + "/4250"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("React Raiders"))
                .andExpect(jsonPath("$.email").value("reactraiders@adp.com"));
    }

    @Test
    void getUser_shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
        Mockito.when(userService.findById(anyString())).thenReturn(null);

        mockMvc.perform(get(BASE_URL + "/4250"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(user));

        mockMvc.perform(get(BASE_URL + "/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("React Raiders"))
                .andExpect(jsonPath("$[0].email").value("reactraiders@adp.com"));
    }

    @Test
    void getAllUsers_shouldReturnNoContentWhenEmpty() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList());

        mockMvc.perform(get(BASE_URL + "/getall"))
                .andExpect(status().isNoContent());
    }
}
