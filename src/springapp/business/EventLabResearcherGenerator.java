package springapp.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import springapp.dao.Dao;
import springapp.model.Event;
import springapp.model.Lab;
import springapp.model.Researcher;
import springapp.model.utils.EventType;
import springapp.model.utils.Role;

public class EventLabResearcherGenerator {

	static Random rnd = new Random();

	static List<Researcher> researchers = new ArrayList<>();

	public static void generateEventsLabsResearchers(Dao dao, int numberOfEvents, int numberOfLabs) {
		// GENERATIONS DES CHERCHEURS ET DES LABS
		List<Lab> labs = new ArrayList<Lab>();
		for (int i = 0; i < numberOfLabs; i++) {
			Faker faker = new Faker(new Locale("FR"));
			labs.add(fillLab(new Lab(faker.animal().name() + "-" + faker.color().name() + "-" + rnd.nextInt(1000))));
		}
		// GENERATIONS D'ORGANISATEURS ET D'EVENT
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < numberOfEvents;) {
			Researcher r = researchers.get(rnd.nextInt(researchers.size()));
			r.setRole(Role.ORGANIZER);
			for (int ii = 0; ii < rnd.nextInt(5) + 1; ii++) {
				Faker faker = new Faker(new Locale("FR"));
				// genere date de l'event
				Date date = new Date();
				Calendar mindate = Calendar.getInstance();
				mindate.setTime(date);
				mindate.add(Calendar.DATE, -500);
				Calendar maxdate = Calendar.getInstance();
				maxdate.setTime(date);
				maxdate.add(Calendar.DATE, +500);
				Date begin = faker.date().between(mindate.getTime(), maxdate.getTime());
				Calendar eventDuration = Calendar.getInstance();
				eventDuration.setTime(begin);
				eventDuration.add(Calendar.DATE, rnd.nextInt(4));
				Date end = eventDuration.getTime();
				// genere speaker funny name
				List<String> speakers = new ArrayList<>();
				for (int iii = 0; iii < rnd.nextInt(5) + 1; iii++)
					speakers.add(faker.funnyName().name());
				Event e = null;

				switch (rnd.nextInt(2)) {
				case (0):
					e = new Event("Congrés de " + faker.ancient().god() + " " + rnd.nextInt(100), EventType.CONGRESS,
							faker.address().city(), begin, end,
							"Description description de ce congrés " + faker.lorem().characters(500), speakers,
							100 + rnd.nextInt(400) + 0.0f, new Long(rnd.nextInt(20) * 100));
					break;
				case (1):
					e = new Event("Séminaire " + faker.app().name() + " " + rnd.nextInt(100), EventType.SEMINAR,
							faker.address().city(), begin, end,
							"Description du séminaire: " + faker.lorem().characters(500), speakers,
							100 + rnd.nextInt(400) + 0.0f, new Long(rnd.nextInt(50)));
					break;
				case (2):
					e = new Event("Conférence " + faker.commerce().department() + " " + rnd.nextInt(100),
							EventType.CONFERENCE, faker.address().city(), begin, end,
							"Description de la conf: " + faker.lorem().characters(500), speakers,
							100 + rnd.nextInt(400) + 0.0f, new Long(rnd.nextInt(80) * 10));
					break;
				}

				e.addOrganizer(r);
				for (int iii = 0; iii < e.getAttendeeCap() || iii < rnd.nextInt(numberOfLabs); iii++) {
					Researcher attendee = researchers.get(rnd.nextInt(researchers.size()));
					if (e.getAttendees() != null && e.getAttendees().contains(attendee))
						continue;
					e.addAttendee(attendee);
				}
				events.add(e);
				i++;
			}
		}

		Faker faker = new Faker(new Locale("FR"));
		if (!dao.hasResearcher("admin@test.test")) {
			Researcher adminTest = new Researcher("admin@test.test", "Adminator", "Adminus", "admin4ever.com",
					faker.date().birthday(), "admin", Role.ADMIN);
			researchers.add(adminTest);
			labs.get(0).addResearcher(adminTest);
		}
		if (!dao.hasResearcher("orga@test.test")) {
			Researcher orgaTest = new Researcher("orga@test.test", "Organisator", "Organum", "orga.com",
					faker.date().birthday(), "orga", Role.ORGANIZER);
			researchers.add(orgaTest);
			labs.get(0).addResearcher(orgaTest);

		}
		if (!dao.hasResearcher("user@test.test")) {
			Researcher userTest = new Researcher("user@test.test", "Userator", "Usarus", "proud2use.com",
					faker.date().birthday(), "user", Role.USER);
			researchers.add(userTest);
			labs.get(0).addResearcher(userTest);
		}

		for (Lab l : labs) {
			dao.addLab(l);
		}
		for (Researcher r : researchers) {
			dao.addResearcher(r);
		}
		for (Event e : events) {
			dao.addEvent(e);
		}
	}

	// Researcher(String email, String firstName, String lastName, String website,
	// Date birthDay, String password, Role role)
	public static Lab fillLab(Lab lab) {
		for (int i = 0; i < rnd.nextInt(18) + 2; i++) {
			Faker faker = new Faker(new Locale("FR"));
			Researcher r = new Researcher(rnd.nextInt(100) + faker.internet().emailAddress(), faker.name().firstName(),
					faker.name().lastName(), faker.internet().url(), faker.date().birthday(),
					faker.internet().password(), Role.USER);
			lab.addResearcher(r);
			researchers.add(r);
		}
		return lab;
	}

}
