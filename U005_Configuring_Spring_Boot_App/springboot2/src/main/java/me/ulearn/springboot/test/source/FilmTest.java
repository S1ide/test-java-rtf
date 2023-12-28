package me.ulearn.springboot.test.source;

import me.ulearn.springboot.config.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FilmTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Film film;

    @Test
    public void assertThatGetFilmInfoReturnedRightResult() throws Exception {
        mockMvc.perform(get("/film"))
                .andExpect(status().isOk())
                .andExpect(content().string(film.toString()))
                .andExpect(content().string("Stranger Things (horror, 2016)"));
    }

    @Test
    public void assertThatGetFilmNameReturnedRightResult() throws Exception {
        mockMvc.perform(get("/film/name"))
                .andExpect(status().isOk())
                .andExpect(content().string(film.getName()))
                .andExpect(content().string("Stranger Things"));
    }

    @Test
    public void assertThatGetGenreNameReturnedRightResult() throws Exception {
        mockMvc.perform(get("/film/genre"))
                .andExpect(status().isOk())
                .andExpect(content().string(film.getGenre()))
                .andExpect(content().string("horror"));
    }

    @Test
    public void assertThatGetYearNameReturnedRightResult() throws Exception {
        mockMvc.perform(get("/film/year"))
                .andExpect(status().isOk())
                .andExpect(content().string(film.getYearOfRelease().toString()))
                .andExpect(content().string("2016"));
    }
}
