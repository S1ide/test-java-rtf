package ulearn.practice3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class Practice3ApplicationTests
{
	@Autowired
	private MockMvc mockMvc;

	@AfterEach
	void reset() throws Exception
	{
		mockMvc.perform(get("/calendar/reset"));
	}

	@Test
	void shouldGetBaseDate() throws Exception
	{
		mockMvc.perform(get("/calendar/get/0"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("1.1.2000")));
	}

	@Test
	void shouldSetValidDate() throws Exception
	{
		setDate("24.6.2002").andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void shouldSendBadRequestWhenInvalidDate() throws Exception
	{
		setDate("-24.6.2000").andDo(print())
				.andExpect(status().isBadRequest());
		setDate("24.-6.2000").andDo(print())
				.andExpect(status().isBadRequest());
		setDate("24.6.-2000").andDo(print())
				.andExpect(status().isBadRequest());
		setDate("242.6.2002").andDo(print())
				.andExpect(status().isBadRequest());
		setDate("2.25.2002").andDo(print())
				.andExpect(status().isBadRequest());
		setDate("2.11.tvtt").andDo(print())
				.andExpect(status().isBadRequest());
		setDate("2,11,2001").andDo(print())
				.andExpect(status().isBadRequest());
		setDate("24.6.20").andDo(print())
				.andExpect(status().isBadRequest());
		setDate("abracadabra").andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldSetAndGetValidDate() throws Exception
	{
		setDate("24.6.2002");
		mockMvc.perform(get("/calendar/get/0"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("24.6.2002")));
	}

	@Test
	void shouldGetLastDateWhenSetNewDate() throws Exception
	{
		setDate("24.6.2002");
		setDate("10.8.2005");
		mockMvc.perform(get("/calendar/get/0"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("10.8.2005")));
	}

	@Test
	void shouldGetBaseDateAfterReset() throws Exception
	{
		setDate("24.6.2002");
		mockMvc.perform(get("/calendar/reset"))
				.andDo(print())
				.andExpect(status().isOk());
		mockMvc.perform(get("/calendar/get/0"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("1.1.2000")));
	}

	@Test
	void shouldGetDateWhenDaysNegative() throws Exception
	{
		setDate("24.6.2002");
		mockMvc.perform(get("/calendar/get/-10"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("14.6.2002")));
	}

	@Test
	void shouldGetDateWhenDaysPositive() throws Exception
	{
		setDate("24.6.2002");
		mockMvc.perform(get("/calendar/get/3"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("27.6.2002")));
	}

	@Test
	void shouldNotChangeStartDateWhenUseGetDaysMethod() throws Exception
	{
		setDate("24.6.2002");
		mockMvc.perform(get("/calendar/get/3"));
		mockMvc.perform(get("/calendar/get/0"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("24.6.2002")));
	}

	@Test
	void shouldGetDateWhenMonthChanged() throws Exception
	{
		setDate("24.6.2002");
		mockMvc.perform(get("/calendar/get/7"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("1.7.2002")));
	}

	@Test
	void shouldGetDateWhenYearChanged() throws Exception
	{
		setDate("31.12.2002");
		mockMvc.perform(get("/calendar/get/2"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("2.1.2003")));
	}

	@Test
	void shouldGetDifferenceWhenDatesEqual() throws Exception
	{
		setDate("24.6.2002");
		mockMvc.perform(get("/calendar/get/difference/24.6.2002"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("0")));
	}

	@Test
	void shouldGetDifferenceWhenDifferencePositive() throws Exception
	{
		setDate("24.6.2002");
		mockMvc.perform(get("/calendar/get/difference/29.6.2002"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("5")));
	}

	@Test
	void shouldGetDifferenceWhenDifferenceNegative() throws Exception
	{
		setDate("24.6.2002");
		mockMvc.perform(get("/calendar/get/difference/4.6.2002"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("20")));
	}

	private ResultActions setDate(String date) throws Exception
	{
		return mockMvc.perform(post("/calendar/set/" + date));
	}
}
