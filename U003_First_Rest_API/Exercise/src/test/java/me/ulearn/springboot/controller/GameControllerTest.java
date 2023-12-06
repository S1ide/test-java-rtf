package me.ulearn.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ulearn.springboot.entity.Game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GameController.class)
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void assertThatGetReturnedRight() throws Exception {
        mockMvc.perform(get("/games"))
                .andExpect(status().isOk());
    }

    @Test
    public void assertThatDeleteGameById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/games/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void addGame() throws Exception {
        Game game = new Game(1L, "Alan Wake 2", "Remedy Entertainment");
        mockMvc.perform(
                        post("/games")
                                .content(objectMapper.writeValueAsString(game))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    public void getExistId() throws Exception {
        mockMvc.perform(
                        get("/games/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("10"))
                .andExpect(jsonPath("$.name").value("Minecraft"));
    }

    @Test
    public void getNonExistId() throws Exception {
        mockMvc.perform(
                        get("/games/1111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void deleteNonExistId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/games/{id}", 11111))
                .andExpect(status().isOk());
    }
}