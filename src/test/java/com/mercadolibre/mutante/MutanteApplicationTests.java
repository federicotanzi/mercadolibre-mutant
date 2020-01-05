package com.mercadolibre.mutante;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.mutante.domain.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
	public void testEmptyStats() throws Exception {
		ResultActions result = mockMvc.perform(
				get("/stats/")
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));

		byte[] content = result.andReturn().getResponse().getContentAsByteArray();

		Status dnaResult = new ObjectMapper().readValue(content, Status.class);
		Assert.assertNotNull(dnaResult);
	}

	@Test
	public void testMutantDiagonalUp() throws Exception {
		/*
		 HORIZONTAL: CCCC ROW 1
		 DIAGONAL UP: TTTT ROW 1
		 */
		String request = "{\n" +
				"	\"dna\": [\"AACCCCT\",\n" +
				"	          \"CTCACTT\",\n" +
				"	          \"ACTCTAT\",\n" +
				"	          \"ACCTACC\",\n" +
				"	          \"CAACTCC\",\n" +
				"	          \"CAACAAT\",\n" +
				"	          \"CAACAAT\"]\n" +
				"}\n" +
				"";
		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(request.getBytes()))
				.andExpect(status().isOk());
	}

	@Test
	public void testMutantDiagonalDown() throws Exception {
		/*
		 HORIZONTAL: TTTT ROW 7
		 DIAGONAL DOWN: AAAA ROW 1
		 */
		String request = "{\n" +
				" \"dna\": [\"AATACCA\",\n" +
				"	        \"CACAATT\",\n" +
				"	        \"ACACTAT\",\n" +
				"	        \"ACCAACA\",\n" +
				"	        \"CAATTCC\",\n" +
				"	        \"CAACAAT\",\n" +
				"	        \"CAATTTT\"]\n" +
				"}\n" +
				"";
		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(request.getBytes()))
				.andExpect(status().isOk());
	}

	@Test
	public void testMutantHorizontal() throws Exception {
		/*
		 HORIZONTAL: CCCC ROW 1
		 HORIZONTAL: CCCC ROW 7
		 */
		String request = "{\n" +
				" \"dna\": [\"ACCCCCT\",\n" +
				"	        \"CTCACTT\",\n" +
				"	        \"ACCCTAT\",\n" +
				"	        \"ACCTACC\",\n" +
				"	        \"CAACTCC\",\n" +
				"	        \"CAACAAT\",\n" +
				"	        \"CAACCCC\"]\n" +
				"}\n" +
				"";
		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(request.getBytes()))
				.andExpect(status().isOk());
	}

	@Test
	public void testMutantVertical() throws Exception {
		/*
		 VERTICAL: AAAA COLUMN 1
		 VERTICAL: AAAA COLUMN 2
		 */
		String request = "{\n" +
				" \"dna\": [\"ACCACCT\",\n" +
				"	        \"CTCACTT\",\n" +
				"	        \"ACCATAT\",\n" +
				"	        \"AAAGACT\",\n" +
				"	        \"CAACTCC\",\n" +
				"	        \"CAACAAT\",\n" +
				"	        \"CAACCCA\"]\n" +
				"}\n" +
				"";
		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(request.getBytes()))
				.andExpect(status().isOk());
	}

	@Test
	public void testMutantFailSequence() throws Exception {
		String request = "{\n" +
				" \"dna\": [\"ACCACCT\",\n" +
				"	        \"CAACCCA\"]\n" +
				"}\n" +
				"";
		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(request.getBytes()))
				.andExpect(status().isForbidden());
	}

}
