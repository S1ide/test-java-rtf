package ulearn.practice4;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Hashtable;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Practice4ApplicationTests
{
	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void shouldCalculateMomentum() throws Exception
	{
		var params = new String[]{"p","m","v"};
		var formula = "momentum";

		postAndExpectOk(formula, params, new double[]{10.0, 5.0, 2.0}, "p");
		postAndExpectOk(formula, params, new double[]{10.0, 5.0, 2.0}, "m");
		postAndExpectOk(formula, params, new double[]{-5.0, 2.0, -2.5}, "v");
	}

	@Test
	void shouldCalculateMomentumChangeLaw() throws Exception
	{
		var params = new String[]{"F","t","m","v","v0"};
		var formula = "momentumChangeLaw";

		postAndExpectOk(formula, params, new double[]{10.0, 2.0, 1.0, 20.0, 0.0}, "F");
		postAndExpectOk(formula, params, new double[]{10.0, 1.0, 1.0, 10.0, 0.0}, "t");
		postAndExpectOk(formula, params, new double[]{-100.0, 5.0, 10.0, 0.0, 50.0}, "m");
		postAndExpectOk(formula, params, new double[]{100.0, 5.0, 10.0, 60.0, 10.0}, "v");
		postAndExpectOk(formula, params, new double[]{100.0, 5.0, 10.0, 60.0, 10.0}, "v0");
	}

	@Test
	void shouldCalculateMomentumSaveLaw() throws Exception
	{
		var params = new String[]{"m1","m2","v1","v01","v2","v02"};
		var formula = "momentumSaveLaw";

		postAndExpectOk(formula, params, new double[]{50.0, 10.0, 30.0, 40.0, 0.0, 50.0}, "m2");
		postAndExpectOk(formula, params, new double[]{50.0, 10.0, 30.0, 40.0, 0.0, 50.0}, "m1");
		postAndExpectOk(formula, params, new double[]{50.0, 10.0, 10.0, 0.0, -50.0, 0.0}, "v1");
		postAndExpectOk(formula, params, new double[]{50.0, 10.0, 30.0, 40.0, 0.0, 50.0}, "v02");
	}

	@Test
	void shouldCalculateSecondNewtonLaw() throws Exception
	{
		var params = new String[]{"F", "m", "a"};
		var formula = "secondNewtonLaw";

		postAndExpectOk(formula, params, new double[]{10.0, 5.0, 2.0}, "F");
		postAndExpectOk(formula, params, new double[]{25.0, 12.5, 2.0}, "m");
		postAndExpectOk(formula, params, new double[]{10.0, 5.0, 2.0}, "a");
	}

	@Test
	void shouldUseDIWhenAllowingFormulas() throws Exception
	{
		var params = new String[]{"F1", "F2"};
		var formula = "thirdNewtonLaw";
		postAndExpectOk(formula, params, new double[]{10.0, -10.0}, "F1");
	}

	@Test
	void shouldSendBadRequestWhenRequestContainsNotAllowedFormula() throws Exception
	{
		var params = new String[]{"p", "m", "v"};
		var formula = "moment";
		postAndExpectBadRequest(formula, params, new double[]{10.0, 5.0, 2.0}, "p");

		params = new String[]{};
		formula = "abcef";
		postAndExpectBadRequest(formula, params, new double[]{}, "a");
	}

	@Test
	void shouldSendBadRequestWhenRequestNotContainsAllParameter() throws Exception
	{
		var params = new String[]{"p", "v", "t"};
		var formula = "momentum";
		postAndExpectBadRequest(formula, params, new double[]{10.0, 2.0, 3.0}, "p");

		params = new String[]{"F","v0"};
		formula = "momentumChangeLaw";
		postAndExpectBadRequest(formula, params, new double[]{5.0, 2.0}, "F");

		params = new String[]{"m1", "m2", "v1",};
		formula = "momentumSaveLaw";
		postAndExpectBadRequest(formula, params, new double[]{5.0, 2.0, 3.0}, "v2");

		params = new String[]{"F", "m", "e"};
		formula = "secondNewtonLaw";
		postAndExpectBadRequest(formula, params, new double[]{2.0, 3.0, 1.0}, "F");
	}

	@Test
	void shouldSendBadRequestWhenRequestContainsWrongParameterValues() throws Exception
	{
		var params = new String[]{"p", "m", "v"};
		var formula = "momentum";

		postAndExpectBadRequest(formula, params, new double[]{10.0, -5.0, -2.0}, "p");

		params = new String[]{"F","t","m","v","v0"};
		formula = "momentumChangeLaw";
		postAndExpectBadRequest(formula, params, new double[]{-10.0, 2.0, -1.0, 20.0, 0.0}, "F");
		postAndExpectBadRequest(formula, params, new double[]{-10.0, -2.0, 1.0, 20.0, 0.0}, "F");

		params = new String[]{"m1","m2","v1","v01","v2","v02"};
		formula = "momentumSaveLaw";

		postAndExpectBadRequest(formula, params, new double[]{-50.0, -10.0, 30.0, 40.0, 0.0, 50.0}, "m2");

		params = new String[]{"F", "m", "a"};
		formula = "secondNewtonLaw";

		postAndExpectBadRequest(formula, params, new double[]{-10.0, -5.0, 2.0}, "F");
	}

	@Test
	void shouldSendBadRequestWhenUnknownParameterIsNotCorrect() throws Exception
	{
		var params = new String[]{"p", "m", "v"};
		var formula = "momentum";

		postAndExpectBadRequest(formula, params, new double[]{10.0, 5.0, 2.0}, "f");

		params = new String[]{"F", "m", "a"};
		formula = "secondNewtonLaw";

		var dict = new Hashtable<String, Double>();
		FillParamsHashtable(params, new double[]{10.0, 5.0, 2.0}, "a", dict);

		postToServer(formula, "m", dict)
				.andExpect(status().isBadRequest());
	}

	private void postAndExpectOk(String formula, String[] params, double[] paramsValue, String unknown) throws Exception
	{
		if(params.length != paramsValue.length)
			throw new Exception("In test Exception. params and paramsValue count not equal");
		var dict = new Hashtable<String, Double>();
		var unknownValue = FillParamsHashtable(params, paramsValue, unknown, dict);
		postToServer(formula, unknown, dict)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(String.valueOf(unknownValue))));
	}

	private void postAndExpectBadRequest(String formula, String[] params, double[] paramsValue, String unknown) throws Exception
	{
		if(params.length != paramsValue.length)
			throw new Exception("In test Exception. params and paramsValue count not equal");
		var dict = new Hashtable<String, Double>();
		FillParamsHashtable(params, paramsValue, unknown, dict);
		postToServer(formula, unknown, dict)
				.andExpect(status().isBadRequest());
	}

	private ResultActions postToServer(String formula, String unknown, Hashtable<String, Double> dict) throws Exception
	{
		String json = objectMapper.writeValueAsString(dict);
		return mockMvc.perform(post(String.format("/physics/calculate/%s/%s", formula, unknown))
						.contentType(MediaType.APPLICATION_JSON)
						.content(json)
						.characterEncoding("utf-8"))
				.andDo(print());
	}

	private static double FillParamsHashtable(String[] params, double[] paramsValue, String unknown,
											Hashtable<String, Double> dict)
	{
		var unknownValue = 0.0;
		for(var i = 0; i < params.length; i++)
		{
			if(params[i].equals(unknown))
				unknownValue = paramsValue[i];
			else
				dict.put(params[i], paramsValue[i]);
		}
		return unknownValue;
	}
}
