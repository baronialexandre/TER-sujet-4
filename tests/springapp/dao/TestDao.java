package springapp.dao;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import springapp.dao.Dao;
import springapp.dao.SpringDAOConfiguration;
import springapp.model.Event;
import springapp.model.Lab;
import springapp.model.Researcher;
import springapp.model.utils.EventType;
import springapp.model.utils.Role;
import springapp.model.utils.User;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringDAOConfiguration.class)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class TestDao {

	@Autowired
	Dao dao;
	Random rnd = new Random();
	Researcher researcherAdd, researcherGet, researcherUpd;
	Lab labAdd, labGet, labUpd;
	Event eventAdd, eventGet, eventUpd;
	
	
	@BeforeAll
	public void beforeAll() {
		researcherAdd = new Researcher("ADD@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		researcherGet = new Researcher("GET@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		researcherUpd = new Researcher("UPD@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		labAdd = new Lab("addlab");
		labGet = new Lab("getlab");
		labUpd = new Lab("updlab");
		eventAdd = new Event("ADD FYRE CONF",EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150));
		eventGet = new Event("GET FYRE CONF",EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150));
		eventUpd = new Event("UPD FYRE CONF",EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150));
		/*
		personAdd = new Person("napolimanADD"+rnd.nextInt(),"NAPOLI","Marcel","napoliman76@gmuil.com","www.napolo.com",new Date(), "pwd");
		personGet = new Person("napolimanGET"+rnd.nextInt(),"NAPOLI","Marcel","napoliman76@gmuil.com","www.napolo.com",new Date(), "pwd");
		personUpd = new Person("napolimanUP"+rnd.nextInt(),"NAPOLI","Marcel","napoliman76@gmuil.com","www.napolo.com",new Date(), "pwd");
		personTeam1 = new Person("Groupie1-"+rnd.nextInt(),"NAPOLI","Marcel","napoliman76@gmuil.com","www.napolo.com",new Date(), "pwd");
		personTeam2 = new Person("Groupie2-"+rnd.nextInt(),"NAPOLI","Marcel","napoliman76@gmuil.com","www.napolo.com",new Date(), "pwd");
		personTeam3 = new Person("Groupie3-"+rnd.nextInt(),"NAPOLI","Marcel","napoliman76@gmuil.com","www.napolo.com",new Date(), "pwd");
		personAuth = new Person("testAuthYes","NAPOLI","Marcel","napoliman76@gmuil.com","www.napolo.com",new Date(), "testAuthPwd");
		personAuth2 = new Person("testAuthNot","NAPOLI","Marcel","napoliman76@gmuil.com","www.napolo.com",new Date(), "testAuthPwd");
		personHas = new Person("usernameAlreadyUsed","BERLINGOT","Freshfreshbo","subaruxxl@outlook.fr","www.lesitedeberlingot.com",new Date(),"pwd");
		teamAdd = new Team("ADD pecheurs du dimanche"+rnd.nextInt());
		teamGet = new Team("GET pecheurs du dimanche"+rnd.nextInt());
		teamUpd = new Team("UP pecheurs du dimanche"+rnd.nextInt());
		team = new Team("Groupe By - SQL les bases "+rnd.nextInt());
		teamGetAll = new Team("GetAllTeam "+rnd.nextInt());
		teamSearch1 = new Team("ADD for testSearchTeams : POISSON : "+rnd.nextInt());
		teamSearch2 = new Team("ADD for testSearchTeams : POISSON 2 : "+rnd.nextInt());
		*/
		//dao.clearDatabase();
	}

	@AfterAll
	public void afterAll() {
		
		/*
		dao.removePerson(personAdd.getPersonId());
		dao.removePerson(personGet.getPersonId());
		dao.removePerson(personUpd.getPersonId());
		dao.removePerson(personTeam1.getPersonId());
		dao.removePerson(personTeam2.getPersonId());
		dao.removePerson(personTeam3.getPersonId());
		dao.removePerson(personAuth.getPersonId());
		dao.removePerson(personAuth2.getPersonId());
		dao.removeTeam(teamAdd.getTeamId());
		dao.removeTeam(teamGet.getTeamId());
		dao.removeTeam(teamUpd.getTeamId());
		dao.removeTeam(team.getTeamId());
		dao.removeTeam(teamGetAll.getTeamId());
		dao.removeTeam(teamSearch1.getTeamId());
		dao.removeTeam(teamSearch2.getTeamId());
		*/
	}

	@BeforeEach
	public void setUp() {
	}

	@AfterEach
	public void tearDown() {
	}
	
	//Researcher(String email, String firstName, String lastName,  String website, Date birthDay, String password, Role role) 
	@Test
	public void testAddResearcher() {
		researcherAdd = dao.addResearcher(researcherAdd);
		assertTrue(true);
	}

	@Test
	public void testFindResearcher() {
		researcherGet = dao.addResearcher(researcherGet);
		Researcher researcherGet2 = dao.findResearcher(researcherGet.getResearcherId());
		assertEquals(researcherGet.getResearcherId(), researcherGet2.getResearcherId());
	}

	@Test
	public void testUpdateResearcher() {
		researcherUpd = dao.addResearcher(researcherUpd);
		researcherUpd.setFirstName("PREMIERnon");
		dao.updateResearcher(researcherUpd);
		assertEquals("PREMIERnon", dao.findResearcher(researcherUpd.getResearcherId()).getFirstName());
	}

	@Test
	public void testRemoveResearcher() {
		Researcher researcherRem = new Researcher("rem@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		researcherRem = dao.addResearcher(researcherRem);
		dao.removeResearcher(researcherRem.getResearcherId());
		try {
			dao.findResearcher(researcherRem.getResearcherId());
		} catch(Exception e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	
	//Lab(String labName)
	@Test
	public void testAddLab() {
		labAdd = dao.addLab(labAdd);
		assertTrue(true);
	}

	@Test
	public void testFindLab() {
		labGet = dao.addLab(labGet);
		Lab labGet2 = dao.findLab(labGet.getLabId());
		assertEquals(labGet.getLabId(), labGet2.getLabId());
	}

	@Test
	public void testUpdateLab() {
		labUpd = dao.addLab(labUpd);
		labUpd.setLabName("UPDATEDlab");
		dao.updateLab(labUpd);
		assertEquals("UPDATEDlab", dao.findLab(labUpd.getLabId()).getLabName());
	}

	@Test
	public void testRemoveLab() {
		Lab labRem = new Lab("rem lab lab");
		labRem = dao.addLab(labRem);
		dao.removeLab(labRem.getLabId());
		try {
			dao.findLab(labRem.getLabId());
		} catch(Exception e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	//Event(String eventName, EventType type, String location, Date beginDate, Date endDate, String description, List<String> speakers, Float fee, Long attendeeCap, Researcher organizer) 
	@Test
	public void testAddEvent() {
		eventAdd = dao.addEvent(eventAdd);
		assertTrue(true);
	}

	@Test
	public void testFindEvent() {
		eventGet = dao.addEvent(eventGet);
		Event eventGet2 = dao.findEvent(eventGet.getEventId());
		assertEquals(eventGet.getEventId(), eventGet2.getEventId());
	}

	@Test
	public void testUpdateEvent() {
		eventUpd = dao.addEvent(eventUpd);
		eventUpd.setEventName("UPDATEDevent");
		dao.updateEvent(eventUpd);
		assertEquals("UPDATEDevent", dao.findEvent(eventUpd.getEventId()).getEventName());
	}

	@Test
	public void testRemoveEvent() {
		Event eventRem = new Event("REM FYRE CONF",EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150));
		eventRem = dao.addEvent(eventRem);
		dao.removeEvent(eventRem.getEventId());
		try {
			dao.findEvent(eventRem.getEventId());
		} catch(Exception e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	/*
	@Test
	public void testAddTeam() {
		teamAdd = dao.addTeam(teamAdd);
		assertTrue(true);
	}

	@Test
	public void testFindTeam() {
		teamGet = dao.addTeam(teamGet);
		Team teamGet2 = dao.findTeam(teamGet.getTeamId());
		assertEquals(teamGet.getTeamId(), teamGet2.getTeamId());
	}

	@Test
	public void testUpdateTeam() {
		teamUpd = dao.addTeam(teamUpd);
		teamUpd.setTeamName("FANCLUB");
		dao.updateTeam(teamUpd);
		assertEquals("FANCLUB", dao.findTeam(teamUpd.getTeamId()).getTeamName());
	}

	@Test
	public void testRemoveTeam() {
		Team teamRem = new Team("ADD pecheurs du dimanche"+rnd.nextInt());
		teamRem = dao.addTeam(teamRem);
		dao.removeTeam(teamRem.getTeamId());
		try {
			dao.findTeam(teamRem.getTeamId());
		} catch(Exception e) {
			assertTrue(true);
			return;
		}
		fail();
	}

	// Person(String username, String firstName, String lastName, String email, String website, Date birthDay, String password)
	@Test
	public void testAddPerson() {
		dao.addPerson(personAdd);
		Person personAddReturned = dao.findPerson(personAdd.getPersonId());
		assertEquals(personAdd.getPersonId(), personAddReturned.getPersonId());
	}

	@Test
	public void testFindPerson() {
		personGet = dao.addPerson(personGet);
		Person personGet2 = dao.findPerson(personGet.getPersonId());
		assertEquals(personGet.getPersonId(), personGet2.getPersonId());
	}

	@Test
	public void testUpdatePerson() {
		personUpd = dao.addPerson(personUpd);
		personUpd.setFirstName("updatedNAPOLIMAN0");
		dao.updatePerson(personUpd);
		
		assertEquals("updatedNAPOLIMAN0", dao.findPerson(personUpd.getPersonId()).getFirstName());
	}

	@Test
	public void testRemovePerson() {
		Person personRem = new Person("napolimanREM"+rnd.nextInt(),"NAPOLI","Marcel","napoliman76@gmuil.com","www.napolo.com",new Date(), "pwd");
		personRem = dao.addPerson(personRem);
		dao.removePerson(personRem.getPersonId());
		try {
			dao.findPerson(personRem.getPersonId());
		} catch(Exception e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	//on fait des liens entre les deux
	@Test
	public void testAddPersonToATeam() {
		team.addPerson(personTeam1);
		team.addPerson(personTeam2);
		team.addPerson(personTeam3);
		team = dao.addTeam(team);
		personTeam1 = dao.addPerson(personTeam1);
		personTeam2 = dao.addPerson(personTeam2);
		personTeam3 = dao.addPerson(personTeam3);
		Set<Long> people = new HashSet<Long>();
		people.add(personTeam1.getPersonId());
		people.add(personTeam2.getPersonId());
		people.add(personTeam3.getPersonId());
		
		Team teamCheck = dao.findTeam(team.getTeamId());
		System.out.println(people);
		System.out.println(teamCheck.getMembers());
		for(Person p : teamCheck.getMembers()) {
			assertTrue(people.contains(p.getPersonId()));
		}
	}
	
	@Test
	public void testGetAllTeam() {
		dao.addTeam(teamGetAll);
		Collection<Team> teams = new ArrayList<>();
		try {
			teams = dao.getAllTeams();
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(teams.size() > 0);
	}
	
	@Test
	public void testAuthUser() {
		dao.addPerson(personAuth);
		User user = new User(personAuth.getUsername(), personAuth.getPassword());
		
		user = dao.authUser(user);
		
		assertEquals(personAuth.getPersonId(), user.getId());
	}
	
	@Test
	public void testAuthUserNot() {
		dao.addPerson(personAuth2);
		User user = new User(personAuth2.getUsername(), "Error");
		user.setId(-1);
		
		user = dao.authUser(user);
		
		assertEquals(-1, user.getId());
	}
	
	@Test
	public void testSearchTeams() {
		
		dao.addTeam(teamSearch1);
		dao.addTeam(teamSearch2);
		
		Collection<Team> teams = new ArrayList<>();
		try {
			teams = dao.searchTeams("POISSON");
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(2, teams.size());
	}
	
	@Test
	public void testHasUser() {
		dao.addPerson(personHas);
		assertTrue(dao.hasPerson(personHas.getUsername()));
		dao.removePerson(personHas.getPersonId());
		assertFalse(dao.hasPerson(personHas.getUsername()));
	}*/
	
}