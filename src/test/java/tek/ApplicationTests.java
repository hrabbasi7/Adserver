package tek;

/**
 * @author Haroon
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import tek.PartnerRepository;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CampaignRepository campaignRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		campaignRepository.deleteAll();
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
				jsonPath("$._links.campaign").exists());
	}

	@Test
	public void shouldNotRetreiveCampaignEntity() throws Exception {

		mockMvc.perform(post("/campaign").content(
				"{\"partner_Id\": \10, \"duration\":\120, \"ad_content\":\"Baggins\" }")).andExpect(
						status().isBadRequest());
	}

//	@Test
//	public void shouldRetrieveEntity() throws Exception {
//
//		MvcResult mvcResult = mockMvc.perform(get("/campaign").content(
//				"{\"partiner_id\": \10}")).andExpect(
//						status().isCreated()).andReturn();
//
//		String resultReponse = mvcResult.getResponse().getHeader("Location");
//		mockMvc.perform(get(resultReponse)).andExpect(status().isOk()).andExpect(
//				jsonPath("$.partnerId").value("10")).andExpect(
//						jsonPath("$.duration").value("120"));
//	}

	@Test
	public void shouldQueryEntity() throws Exception {

		mockMvc.perform(get("/campaign").content(
				"{ \"partner_id\": \10}")).andExpect(
						status().isOk());

	}

}