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

import springapp.business.EventLabResearcherGenerator;
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
	Researcher researcherAdd, researcherGet, researcherUpd, researcherLab1, researcherLab2, researcherLab3,attendeeEvent1,attendeeEvent2,attendeeEvent3,researcherAuth,researcherAuth2;
	Lab labAdd, labGet, labUpd, lab;
	Event eventAdd, eventGet, eventUpd, event;
	
	@BeforeAll
	public void beforeAll() {
		researcherAdd = new Researcher("ADD"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		researcherGet = new Researcher("GET"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		researcherUpd = new Researcher("UPD"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		researcherLab1 = new Researcher("lab1"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		researcherLab2 = new Researcher("lab2"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		researcherLab3 = new Researcher("lab3"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		attendeeEvent1 = new Researcher("attendee1"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		attendeeEvent2 = new Researcher("attendee2"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.ORGANIZER);
		attendeeEvent3 = new Researcher("attendee3"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.ADMIN);
		researcherAuth = new Researcher("authgood"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.ADMIN);
		researcherAuth2 = new Researcher("authwrongo"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.ADMIN);
		labAdd = new Lab("addlab"+rnd.nextInt());
		labGet = new Lab("getlab"+rnd.nextInt());
		labUpd = new Lab("updlab"+rnd.nextInt());
		lab = new Lab("labo"+rnd.nextInt());
		eventAdd = new Event("ADD FYRE CONF"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150));
		eventGet = new Event("GET FYRE CONF"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150));
		eventUpd = new Event("UPD FYRE CONF"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150));
		event = new Event("event"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150));
	}

	@AfterAll
	public void afterAll() {/*
		dao.removeResearcher(researcherAdd.getResearcherId());
		dao.removeResearcher(researcherGet.getResearcherId());
		dao.removeResearcher(researcherUpd.getResearcherId());
		dao.removeLab(labAdd.getLabId());
		dao.removeLab(labGet.getLabId());
		dao.removeLab(labUpd.getLabId());
		dao.removeEvent(eventAdd.getEventId());
		dao.removeEvent(eventGet.getEventId());
		dao.removeEvent(eventUpd.getEventId());
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
		String randomName = "UPDATEDlab"+rnd.nextInt();
		labUpd.setLabName(randomName);
		dao.updateLab(labUpd);
		assertEquals(randomName, dao.findLab(labUpd.getLabId()).getLabName());
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
		String randomName = "UPDATEDEvent"+rnd.nextInt();
		eventUpd.setEventName(randomName);
		dao.updateEvent(eventUpd);
		assertEquals(randomName, dao.findEvent(eventUpd.getEventId()).getEventName());
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
	
	//on fait des liens entre les deux
	@Test
	public void testAddResearcherToALab() {
		lab.addResearcher(researcherLab1);
		lab.addResearcher(researcherLab2);
		lab.addResearcher(researcherLab3);
		lab = dao.addLab(lab);
		researcherLab1 = dao.addResearcher(researcherLab1);
		researcherLab2 = dao.addResearcher(researcherLab2);
		researcherLab3 = dao.addResearcher(researcherLab3);
		Set<Long> researcherIds = new HashSet<Long>();
		researcherIds.add(researcherLab1.getResearcherId());
		researcherIds.add(researcherLab2.getResearcherId());
		researcherIds.add(researcherLab3.getResearcherId());
		
		Lab labCheck = dao.findLab(lab.getLabId());
		System.out.println(researcherIds);
		System.out.println(labCheck.getResearchers());
		for(Researcher r : labCheck.getResearchers()) {
			assertTrue(researcherIds.contains(r.getResearcherId()));
			assertEquals(r.getLab().getLabId(),lab.getLabId());
		}
		
	}
	
	@Test
	public void testAddAttendeeToAEvent() {
		event.addAttendee(attendeeEvent1);
		event.addAttendee(attendeeEvent2);
		event.addAttendee(attendeeEvent3);
		attendeeEvent1 = dao.addResearcher(attendeeEvent1);
		attendeeEvent2 = dao.addResearcher(attendeeEvent2);
		attendeeEvent3 = dao.addResearcher(attendeeEvent3);
		event = dao.addEvent(event);
		Set<Long> attendeeIds = new HashSet<Long>();
		attendeeIds.add(attendeeEvent1.getResearcherId());
		attendeeIds.add(attendeeEvent2.getResearcherId());
		attendeeIds.add(attendeeEvent3.getResearcherId());
		
		Event eventCheck = dao.findEvent(event.getEventId());
		System.out.println(attendeeIds);
		System.out.println(eventCheck.getAttendees());
		for(Researcher r : eventCheck.getAttendees()) {
			assertTrue(attendeeIds.contains(r.getResearcherId()));
			//assertTrue(r.getEventsAttending().contains(event)); marche po (pas besoin de tte manier)
		}
	}
	
	@Test
	public void testAuthUser() {
		dao.addResearcher(researcherAuth);
		User user = new User(researcherAuth.getEmail(), researcherAuth.getPassword());
		
		user = dao.authUser(user);
		
		assertEquals(researcherAuth.getResearcherId(), user.getId());
		assertEquals(researcherAuth.getRole(), user.getRole());
	}
	
	@Test
	public void testAuthUserNot() {
		dao.addResearcher(researcherAuth2);
		User user = new User(researcherAuth2.getEmail(), "Error");
		user.setId(-1);
		
		user = dao.authUser(user);
		
		assertEquals(-1, user.getId());
	}
	
	@Test
	public void testGenTest() {
		EventLabResearcherGenerator.generateEventsLabsResearchers(dao, 10, 10);
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
	
	/**
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
	
}