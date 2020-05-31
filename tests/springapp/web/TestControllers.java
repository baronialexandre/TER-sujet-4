package springapp.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import springapp.business.ILabManager;
import springapp.business.IResearcherManager;
import springapp.model.utils.Role;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=SpringStart.class)
public class TestControllers {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	ILabManager labManager;
	@Autowired
	IResearcherManager researcherManager;
	
	private MockMvc mockMvc;
	@BeforeEach
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	    System.out.println("MockMvc : "+mockMvc);
	}
	
	@Test
	public void TestSetupIsGood() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	}
	
	@Test
	public void testLoginAdminController() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").param("email", "admin@test.test").param("password", "admin");
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testLoginOrganizerController() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").param("email", "orga@test.test").param("password", "orga");
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testLoginUserController() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").param("email", "user@test.test").param("password", "user");
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
		
		Assert.assertTrue(true);
	}
	
	@Test
	public void testLoginWrongController() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").param("email", "notAnUser@test.test").param("password", "password");
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/login.jsp"));
	}
	
	@Test
	public void testCreateEventControllerAccessAdmin() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/create-event").sessionAttr("userId", new Long(356)).sessionAttr("userRole", Role.ADMIN);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testCreateEventControllerAccessOrga() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/create-event").sessionAttr("userId", new Long(357)).sessionAttr("userRole", Role.ORGANIZER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testCreateEventControllerAccessUser() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/create-event").sessionAttr("userId", new Long(358)).sessionAttr("userRole", Role.USER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testEditEventControllerAccessAdmin() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-event?eventId=403").sessionAttr("userId", new Long(356)).sessionAttr("userRole", Role.ADMIN);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testEditEventControllerAccessOrgaNotInLabo() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-event?eventId=403").sessionAttr("userId", new Long(357)).sessionAttr("userRole", Role.ORGANIZER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testEditEventControllerAccessUser() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-event?eventId=403").sessionAttr("userId", new Long(358)).sessionAttr("userRole", Role.USER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testAdminPanelControllerAccessAdmin() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/admin-panel").sessionAttr("userId", new Long(356)).sessionAttr("userRole", Role.ADMIN);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testAdminPanelControllerAccessOrganizer() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/admin-panel").sessionAttr("userId", new Long(357)).sessionAttr("userRole", Role.ORGANIZER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/login.jsp"));
	}
	
	@Test
	public void testAdminPanelControllerAccessUser() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/admin-panel").sessionAttr("userId", new Long(358)).sessionAttr("userRole", Role.USER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/login.jsp"));
	}
	
	@Test
	public void testEditProfileControllerAccessAdmin() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId=356").sessionAttr("userId", new Long(356)).sessionAttr("userRole", Role.ADMIN);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testEditOtherUserProfileControllerAccessAdmin() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId=357").sessionAttr("userId", new Long(356)).sessionAttr("userRole", Role.ADMIN);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testEditProfileControllerAccessOrga() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId=357").sessionAttr("userId", new Long(357)).sessionAttr("userRole", Role.ORGANIZER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testEditOtherUserProfileControllerAccessOrga() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId=358").sessionAttr("userId", new Long(357)).sessionAttr("userRole", Role.ORGANIZER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testEditProfileControllerAccessUser() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId=358").sessionAttr("userId", new Long(358)).sessionAttr("userRole", Role.USER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testEditOtherUserProfileControllerAccessUser() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId=357").sessionAttr("userId", new Long(358)).sessionAttr("userRole", Role.USER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	//356 admin
	//357 orga
	//358 user
	
	//.sessionAttr("exampleEntity", exampleEntity)
	
	//.flashAttr("exampleEntity", new ExampleEntity()));

}