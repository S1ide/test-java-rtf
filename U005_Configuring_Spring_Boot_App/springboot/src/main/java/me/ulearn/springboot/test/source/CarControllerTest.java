package me.ulearn.springboot.test.source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void assertThatGetCarBrandReturnedStatusOkAndReturnedRightBrand() throws Exception {
        mockMvc.perform(get("/car/brand"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Toyota Camry 3.5")));
    }

    @Test
    public void assertThatGetCarPowerReturnedStatusOkAndReturnedRightPower() throws Exception {
        mockMvc.perform(get("/car/power"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("298")));
    }

    @Test
    public void assertThatGetCarReleaseYearReturnedStatusOkAndReturnedRightReleaseYear() throws Exception {
        mockMvc.perform(get("/car/releaseYear"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("2022")));
    }

    @Test
    public void assertThatGetCarTankCapacityReturnedStatusOkAndReturnedRightTankCapacity() throws Exception {
        mockMvc.perform(get("/car/tankCapacity"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("60")));
    }
}
