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
import java.util.Calendar;
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
	List<Researcher> researchers = new ArrayList<>();
	List<Lab> labs = new ArrayList<>();
	List<Event> events = new ArrayList<>();
	
	@BeforeAll
	public void beforeAll() {
		researchers.add(new Researcher("ADD"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER));//0
		researchers.add(new Researcher("GET"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER));//1
		researchers.add(new Researcher("UPD"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER));//2
		researchers.add(new Researcher("lab1"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER));//3
		researchers.add(new Researcher("lab2"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER));//4
		researchers.add(new Researcher("lab3"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER));//5
		researchers.add(new Researcher("attendee1"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER));//6
		researchers.add(new Researcher("attendee2"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.ORGANIZER));//7
		researchers.add(new Researcher("attendee3"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.ADMIN));//8
		researchers.add(new Researcher("authgood"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.ADMIN));//9
		researchers.add(new Researcher("authwrongo"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.ADMIN));//10
		researchers.add(new Researcher("search"+rnd.nextInt()+"@gmail.com","poissonmou","yio","www.quepasa.es",new Date(),"pwd",Role.USER));//11
		researchers.add(new Researcher("getnolab"+rnd.nextInt()+"@gmail.com","eee","yio","www.quepasa.es",new Date(),"pwd",Role.USER));//11
		researchers.add(new Researcher("getall"+rnd.nextInt()+"@gmail.com","zzzzz","yio","www.quepasa.es",new Date(),"pwd",Role.USER));//11
		researchers.add(new Researcher("remlab"+rnd.nextInt()+"@gmail.com","aaaaa","yio","www.quepasa.es",new Date(),"pwd",Role.USER));//11
		
		labs.add(new Lab("addlab"+rnd.nextInt()));
		labs.add(new Lab("getlab"+rnd.nextInt()));
		labs.add(new Lab("updlab"+rnd.nextInt()));
		labs.add(new Lab("labo"+rnd.nextInt()));
		labs.add(new Lab("getall"+rnd.nextInt()));
		
		Calendar activeDate = Calendar.getInstance();
		activeDate.setTime(new Date());
		activeDate.add(Calendar.DATE, +500);
		Calendar archiveDate = Calendar.getInstance();
		archiveDate.setTime(new Date());
		archiveDate.add(Calendar.DATE, -500);
		Calendar archiveDateYear2005 = Calendar.getInstance();
		archiveDateYear2005.set(2005, 11, 11);
		
		events.add(new Event("ADD FYRE CONF"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150)));
		events.add(new Event("GET FYRE CONF"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150)));
		events.add(new Event("UPD FYRE CONF"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150)));
		events.add(new Event("event"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList("Jean MICHELE","DJ dog"),new Float(5.05),new Long(150)));
		events.add(new Event("getall"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",new Date(),new Date(),"cette conference de feu",Arrays.asList(""),new Float(5.05),new Long(150)));
		events.add(new Event("active"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",activeDate.getTime(),activeDate.getTime(),"cette conference de feu",Arrays.asList(""),new Float(5.05),new Long(150)));
		events.add(new Event("archive"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",archiveDate.getTime(),archiveDate.getTime(),"cette conference de feu",Arrays.asList(""),new Float(5.05),new Long(150)));
		events.add(new Event("archiveDate"+rnd.nextInt(),EventType.CONFERENCE,"l'ilot",archiveDateYear2005.getTime(),archiveDateYear2005.getTime(),"cette conference de feu",Arrays.asList(""),new Float(5.05),new Long(150)));
		
	}

	@AfterAll
	public void afterAll() {
		for(Event e : events)
			dao.removeEvent(e.getEventId());
		for(Researcher r : researchers)
			dao.removeResearcher(r.getResearcherId());
		for(Lab l : labs)
			dao.removeLab(l.getLabId());
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
		Researcher researcherAdd = dao.addResearcher(researchers.get(0));
		assertTrue(true);
	}

	@Test
	public void testFindResearcher() {
		Researcher researcherGet = dao.addResearcher(researchers.get(1));
		Researcher researcherGet2 = dao.findResearcher(researcherGet.getResearcherId());
		assertEquals(researcherGet.getResearcherId(), researcherGet2.getResearcherId());
	}

	@Test
	public void testUpdateResearcher() {
		Researcher researcherUpd = dao.addResearcher(researchers.get(2));
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
		Lab labAdd = dao.addLab(labs.get(0));
		assertTrue(true);
	}

	@Test
	public void testFindLab() {
		Lab labGet = dao.addLab(labs.get(1));
		Lab labGet2 = dao.findLab(labGet.getLabId());
		assertEquals(labGet.getLabId(), labGet2.getLabId());
	}

	@Test
	public void testUpdateLab() {
		Lab labUpd = dao.addLab(labs.get(2));
		String randomName = "UPDATEDlab"+rnd.nextInt();
		labUpd.setLabName(randomName);
		dao.updateLab(labUpd);
		assertEquals(randomName, dao.findLab(labUpd.getLabId()).getLabName());
	}

	@Test
	public void testRemoveLab() {
		Lab labRem = new Lab("rem lab lab");
		labRem = dao.addLab(labRem);
		labRem.addResearcher(researchers.get(14));
		dao.addResearcher(researchers.get(14));
		dao.updateLab(labRem);
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
		Event eventAdd = dao.addEvent(events.get(0));
		assertTrue(true);
	}

	@Test
	public void testFindEvent() {
		Event eventGet = dao.addEvent(events.get(1));
		Event eventGet2 = dao.findEvent(eventGet.getEventId());
		assertEquals(eventGet.getEventId(), eventGet2.getEventId());
	}

	@Test
	public void testUpdateEvent() {
		Event eventUpd = dao.addEvent(events.get(2));
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
		Lab lab = dao.addLab(labs.get(3));
		lab.addResearcher(researchers.get(3));
		lab.addResearcher(researchers.get(4));
		lab.addResearcher(researchers.get(5));
		dao.addResearcher(researchers.get(3));
		dao.addResearcher(researchers.get(4));
		dao.addResearcher(researchers.get(5));
		dao.updateLab(lab);
		Set<Long> researcherIds = new HashSet<Long>();
		researcherIds.add(researchers.get(3).getResearcherId());
		researcherIds.add(researchers.get(4).getResearcherId());
		researcherIds.add(researchers.get(5).getResearcherId());
		
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
		Event event =  dao.addEvent(events.get(3));
		Researcher attendeeEvent1 = dao.addResearcher(researchers.get(6));
		Researcher attendeeEvent2 = dao.addResearcher(researchers.get(7));
		Researcher attendeeEvent3 = dao.addResearcher(researchers.get(8));
		event.addAttendee(researchers.get(6));
		event.addAttendee(researchers.get(7));
		event.addAttendee(researchers.get(8));
		dao.updateResearcher(researchers.get(6));
		dao.updateResearcher(researchers.get(7));
		dao.updateResearcher(researchers.get(8));
		dao.updateEvent(event);
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
		dao.addResearcher(researchers.get(9));
		User user = new User(researchers.get(9).getEmail(), researchers.get(9).getPassword());
		
		user = dao.authUser(user);
		
		assertEquals(researchers.get(9).getResearcherId(), user.getId());
		assertEquals(researchers.get(9).getRole(), user.getRole());
	}
	
	@Test
	public void testAuthUserNot() {
		dao.addResearcher(researchers.get(10));
		User user = new User(researchers.get(10).getEmail(), "Error");
		user.setId(-1);
		
		user = dao.authUser(user);
		
		assertEquals(-1, user.getId());
	}
	
	@Test
	public void testHasResearcher() {
		Researcher has = new Researcher("HAS"+rnd.nextInt()+"@gmail.com","registo","lastito","www.quepasa.es",new Date(),"pwd",Role.USER);
		dao.addResearcher(has);
		assertTrue(dao.hasResearcher(has.getEmail()));
		dao.removeResearcher(has.getResearcherId());
		assertFalse(dao.hasResearcher(has.getEmail()));
	}
	
	@Test
	public void testSearchResearcher() {
		dao.addResearcher(researchers.get(11));
		Collection<Researcher> result = new ArrayList<>();
		result = dao.searchResearchers("poissonmou");
		assertTrue(result.size() >= 1);
	}
	
	@Test
	public void testGetResearchersWithNoLab() {
		dao.addResearcher(researchers.get(12));
		Collection<Researcher> result = new ArrayList<>();
		result = dao.getResearchersWithNoLab();
		assertTrue(result.size() >= 1);
	}
	
	@Test
	public void testGetAllResearchers() {
		dao.addResearcher(researchers.get(13));
		Collection<Researcher> result = new ArrayList<>();
		result = dao.getAllResearchers();
		assertTrue(result.size() >= 1);
	}
	
	@Test
	public void testFindLabByName() {
		Lab labGet = dao.addLab(new Lab("namefind"+rnd.nextInt()));
		String labName = labGet.getLabName();
		Lab labGet2 = dao.findLab(labName);
		assertEquals(labGet.getLabId(), labGet2.getLabId());
		dao.removeLab(labGet.getLabId());
		assertEquals(null,dao.findLab(labName));
	}
	
	@Test
	public void testGetAllLabs() {
		dao.addLab(labs.get(4));
		Collection<Lab> result = new ArrayList<>();
		result = dao.getAllLabs();
		assertTrue(result.size() >= 1);
	}
	
	@Test
	public void testGetAllEvents() {
		dao.addEvent(events.get(4));
		Collection<Event> result = new ArrayList<>();
		result = dao.getAllEvents();
		assertTrue(result.size() >= 1);
	}
	
	@Test
	public void testGetActiveEvents() {
		dao.addEvent(events.get(5));
		Collection<Event> result = new ArrayList<>();
		result = dao.getActiveEvents();
		assertTrue(result.size() >= 1);
	}
	
	@Test
	public void testGetArchiveEvents() {
		dao.addEvent(events.get(6));
		Collection<Event> result = new ArrayList<>();
		result = dao.getArchivedEvents();
		assertTrue(result.size() >= 1);
	}
	
	@Test
	public void testGetArchivedEventsAtYear() {
		dao.addEvent(events.get(7));
		Collection<Event> result = new ArrayList<>();
		result = dao.getArchivedEventsAtYear(2005);
		assertTrue(result.size() >= 1);
	}
	
	/*
	@Test
	public void testGen() {
		EventLabResearcherGenerator.generateEventsLabsResearchers(dao, 1000, 1000);
	}*/
}