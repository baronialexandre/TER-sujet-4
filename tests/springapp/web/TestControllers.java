package springapp.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
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

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

import springapp.business.ILabManager;
import springapp.business.IResearcherManager;
import springapp.dao.Dao;
import springapp.model.Event;
import springapp.model.Researcher;
import springapp.model.utils.Role;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
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
	@Autowired
	Dao dao;
	
	private MockMvc mockMvc;
	
	private Long admIndex;
	private Long orgaIndex;
	private Long usrIndex;

	
	@BeforeAll
	public void createUsers() {	
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	    System.out.println("MockMvc : "+mockMvc);
	    
		Researcher adm = new Researcher("admin@test.controller", "adm", "inistrator",  "", Date.from(Instant.now()), "admin", Role.ADMIN);
		Researcher orga = new Researcher("orga@test.controller", "orga", "nizer",  "", Date.from(Instant.now()), "orga", Role.ORGANIZER);
		Researcher usr = new Researcher("user@test.controller", "us", "er",  "", Date.from(Instant.now()), "user", Role.USER);
		
		dao.addResearcher(adm);
		dao.addResearcher(orga);
		dao.addResearcher(usr);
	    
		Collection<Researcher> collec = dao.getAllResearchers().stream().filter(r -> r.getEmail().contentEquals("admin@test.controller")).collect(Collectors.toCollection(HashSet::new));
	    admIndex = ((Researcher)(collec.toArray()[0])).getResearcherId();
	    collec = dao.getAllResearchers().stream().filter(r -> r.getEmail().contentEquals("orga@test.controller")).collect(Collectors.toCollection(HashSet::new));
	    orgaIndex = ((Researcher)(collec.toArray()[0])).getResearcherId();
	    collec = dao.getAllResearchers().stream().filter(r -> r.getEmail().contentEquals("user@test.controller")).collect(Collectors.toCollection(HashSet::new));
	    usrIndex = ((Researcher)(collec.toArray()[0])).getResearcherId();
	}
	
	@Test
	public void testSetupIsGood() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	}
	
	@Test
	public void testLoginAdminController() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").param("email", "admin@test.controller").param("password", "admin");
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testLoginOrganizerController() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").param("email", "orga@test.controller").param("password", "orga");
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testLoginUserController() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").param("email", "user@test.controller").param("password", "user");
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
		
		Assert.assertTrue(true);
	}
	
	@Test
	public void testLoginWrongController() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").param("email", "notAnUser@test.controller").param("password", "password");
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/login.jsp"));
	}
	
	@Test
	public void testCreateEventControllerAccessAdmin() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/create-event").sessionAttr("userId", admIndex).sessionAttr("userRole", Role.ADMIN);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testCreateEventControllerAccessOrga() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/create-event").sessionAttr("userId", orgaIndex).sessionAttr("userRole", Role.ORGANIZER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testCreateEventControllerAccessUser() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/create-event").sessionAttr("userId", usrIndex).sessionAttr("userRole", Role.USER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testEditEventControllerAccessAdmin() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		Long eventId = ((Event)(dao.getActiveEvents().toArray()[0])).getEventId();
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-event?eventId="+eventId).sessionAttr("userId", admIndex).sessionAttr("userRole", Role.ADMIN);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testEditEventControllerAccessOrgaNotInLabo() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		Long eventId = ((Event)(dao.getActiveEvents().toArray()[0])).getEventId();
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-event?eventId="+eventId).sessionAttr("userId", orgaIndex).sessionAttr("userRole", Role.ORGANIZER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testEditEventControllerAccessUser() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		Long eventId = ((Event)(dao.getActiveEvents().toArray()[0])).getEventId();
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-event?eventId="+eventId).sessionAttr("userId", usrIndex).sessionAttr("userRole", Role.USER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testAdminPanelControllerAccessAdmin() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/admin-panel").sessionAttr("userId", admIndex).sessionAttr("userRole", Role.ADMIN);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testAdminPanelControllerAccessOrganizer() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/admin-panel").sessionAttr("userId", orgaIndex).sessionAttr("userRole", Role.ORGANIZER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/login.jsp"));
	}
	
	@Test
	public void testAdminPanelControllerAccessUser() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/admin-panel").sessionAttr("userId", usrIndex).sessionAttr("userRole", Role.USER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/login.jsp"));
	}
	
	@Test
	public void testEditProfileControllerAccessAdmin() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId="+admIndex).sessionAttr("userId", admIndex).sessionAttr("userRole", Role.ADMIN);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testEditOtherUserProfileControllerAccessAdmin() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId="+orgaIndex).sessionAttr("userId", admIndex).sessionAttr("userRole", Role.ADMIN);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testEditProfileControllerAccessOrga() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId="+orgaIndex).sessionAttr("userId", orgaIndex).sessionAttr("userRole", Role.ORGANIZER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testEditOtherUserProfileControllerAccessOrga() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId="+usrIndex).sessionAttr("userId", orgaIndex).sessionAttr("userRole", Role.ORGANIZER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@Test
	public void testEditProfileControllerAccessUser() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().is(200);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId="+usrIndex).sessionAttr("userId", usrIndex).sessionAttr("userRole", Role.USER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl(null));
	}
	
	@Test
	public void testEditOtherUserProfileControllerAccessUser() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isFound();
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/edit-profile?researcherId="+orgaIndex).sessionAttr("userId", usrIndex).sessionAttr("userRole", Role.USER);
		
		this.mockMvc.perform(builder).andExpect(ok).andExpect(redirectedUrl("/actions/events"));
	}
	
	@AfterAll
	public void removeUsers() {
		dao.removeResearcher(admIndex);
		dao.removeResearcher(orgaIndex);
		dao.removeResearcher(usrIndex);
	}

}