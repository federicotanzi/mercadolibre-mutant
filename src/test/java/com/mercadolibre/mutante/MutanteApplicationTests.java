package com.mercadolibre.mutante;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class MutanteApplicationTests {

	@Autowired
	private WebApplicationContext appContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void test04MutantDiagnonalUPLastPositionValid() throws Exception {
		String request = "{\n" +
				"	\"dna\": [\"AACCCC\",\n" +
				"	        \"CTCAGC\",\n" +
				"	        \"ACCAAA\",\n" +
				"	        \"ACAAAC\",\n" +
				"	        \"CAAATC\",\n" +
				"	        \"CAACAA\"]\n" +
				"}\n" +
				"";
		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(request.getBytes()))
				.andExpect(status().isOk());
	}

}
