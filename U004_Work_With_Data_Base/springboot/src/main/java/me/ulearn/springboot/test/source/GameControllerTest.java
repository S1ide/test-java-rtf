package me.ulearn.springboot.test.source;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ulearn.springboot.entity.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void assertThatGetGamesReturnedStatusOk() throws Exception {
        mockMvc.perform(get("/games"))
                .andExpect(status().isOk());
    }

    @Test
    public void assertThatDeleteExistsGameByIdReturnedStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/games/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void assertThatAddGameReturnedStatusOkAndReturnedItSelf() throws Exception {
        Game game = new Game(1L, "Alan Wake 2", "Remedy Entertainment");
        mockMvc.perform(post("/games")
                        .content(mapper.writeValueAsString(game))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(game)));
    }

    @Test
    public void assertThatGetExistsGameByIdReturnedStatusOkAndReturnedRightIdAndName() throws Exception {
        mockMvc.perform(get("/games/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("10"))
                .andExpect(jsonPath("$.name").value("Minecraft"));
    }

    @Test
    public void assertThatGetNonExistsGameByIdReturnedStatusOkAndReturnedEmptyBody() throws Exception {
        mockMvc.perform(get("/games/1111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void assertThatDeleteNonExistsGameByIdReturnedStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/games/{id}", 11111))
                .andExpect(status().isOk());
    }
}
