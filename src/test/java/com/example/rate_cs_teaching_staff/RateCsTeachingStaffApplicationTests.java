package com.example.rate_cs_teaching_staff;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.rate_cs_teaching_staff.models.Staff;
import com.example.rate_cs_teaching_staff.models.UsersRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RateCsTeachingStaffApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UsersRepository userRepo;

	@Test
	void contextLoads() {
	}

	// ==========================================
	// WEB / CONTROLLER TESTS
	// ==========================================

	@Test
	public void testGetIndexReturns200AndModel() throws Exception {
		mockMvc.perform(get("/users/view"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("staff"))
				.andExpect(view().name("users/showAll"));
	}

	// ==========================================
	// VALIDATION TESTS
	// ==========================================

	@Test
	public void testPostCreateSuccessRedirects() throws Exception {
		mockMvc.perform(post("/users/add")
				.param("name", "Test Professor")
				.param("email", "test@sfu.ca")
				.param("roleType", "PROF")
				.param("clarity", "8")
				.param("niceness", "9")
				.param("knowledgeableScore", "10")
				.param("comment", "Great prof!"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/users/view"));
	}

	@Test
	public void testPostCreateRejectsInvalidEmailAndScores() throws Exception {
		mockMvc.perform(post("/users/add")
				.param("name", "Bad Data User")
				.param("email", "not-an-email")
				.param("roleType", "TA")
				.param("clarity", "15")
				.param("niceness", "5")
				.param("knowledgeableScore", "5")
				.param("comment", ""))
				.andExpect(status().isBadRequest())
				.andExpect(model().attributeExists("invalidData"))
				.andExpect(view().name("users/add"));
	}

	// ==========================================
	// PERSISTENCE TESTS
	// ==========================================

	@Test
	public void testSaveAndRetrieveEntry() {
		Staff newStaff = new Staff(0, "Jane Doe", "jane@sfu.ca", "TA", 9, 9, 9, "Very helpful.");
		Staff savedStaff = userRepo.save(newStaff);

		Optional<Staff> retrievedStaff = userRepo.findById(savedStaff.getId());

		assertTrue(retrievedStaff.isPresent());
		assertEquals("Jane Doe", retrievedStaff.get().getName());
		assertEquals("TA", retrievedStaff.get().getRoleType());
	}

	@Test
	public void testDeleteRemovesEntry() {
		Staff toDelete = new Staff(0, "Delete Me", "delete@sfu.ca", "STAFF", 5, 5, 5, "");
		Staff savedStaff = userRepo.save(toDelete);

		assertTrue(userRepo.findById(savedStaff.getId()).isPresent());

		userRepo.delete(savedStaff);

		assertTrue(userRepo.findById(savedStaff.getId()).isEmpty());
	}

	@Test
	public void testPostCreateRejectsMissingFields() throws Exception {
		// Simulates a user submitting the form with an entirely blank name
		mockMvc.perform(post("/users/add")
				.param("name", "    ") 
				.param("email", "valid@sfu.ca")
				.param("roleType", "TA")
				.param("clarity", "8")
				.param("niceness", "8")
				.param("knowledgeableScore", "8")
				.param("comment", ""))
				.andExpect(status().isBadRequest())
				.andExpect(model().attributeExists("invalidData"))
				.andExpect(view().name("users/add"));
	}
}