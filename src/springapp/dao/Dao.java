package springapp.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;



import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springapp.model.Event;
import springapp.model.Lab;
import springapp.model.Researcher;
import springapp.model.utils.Role;
import springapp.model.utils.User;

@Service
@Repository
@Transactional
public class Dao {

	
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	EntityManager em;
	
	// DAO RESEARCHER
	public Researcher addResearcher(Researcher r) {
		em.persist(r);
		System.out.println("addResearcher witdh id=" + r.getResearcherId());
		return r;
	}

	public Researcher findResearcher(long id) {
		Researcher r = em.find(Researcher.class, id);
		System.out.println("findResearcher with id=" + r.getResearcherId());
		//loading lazy all the event lists
		if(r.getEventsAttending() != null && r.getEventsAttending().size()!=0)
			System.out.println("events attending: " + r.getEventsAttending().size());
		if(r.getEventsOrganized() != null && r.getEventsOrganized().size()!=0)
			System.out.println("events organized: " + r.getEventsOrganized().size());
		return r;
	}
	
	public void updateResearcher(Researcher r) {
		em.merge(r);
		System.out.println("updateResearcher with id=" + r.getResearcherId());
	}

	public void removeResearcher(long id) {
		em.remove(em.merge(findResearcher(id)));
		System.out.println("removeResearcher with id=" + id);
	}
	
	public Collection<Researcher> getAllResearchers(){
		Collection<Researcher> researchersLazy = em.createQuery("Select r from Researcher r", Researcher.class).getResultList();
		Collection<Researcher> researchers = new ArrayList<Researcher>();
		for(Researcher r : researchersLazy) {
			researchers.add(this.findResearcher(r.getResearcherId()));
		}
		return researchers;
	}
	
	public boolean hasResearcher(String email) {
		System.out.println("find " + email);
		Query query = em.createQuery("SELECT r.id FROM Researcher AS r WHERE email=?1").setParameter(1, email);
		return !query.getResultList().isEmpty();
	}
	
	// DAO LAB
	public Lab addLab(Lab l) {
		em.persist(l);
		System.out.println("addLab witdh id=" + l.getLabId());
		return l;
	}

	public Lab findLab(long id) {
		Lab l = em.find(Lab.class, id);
		System.out.println("findLab with id=" + l.getLabId());
		//loading lazy all the collections
		if(l.getResearchers().size()!=0)
			System.out.println("researcher count: " + l.getResearchers().size());
		return l;
	}
	
	public void updateLab(Lab l) {
		em.merge(l);
		System.out.println("updateLab with id=" + l.getLabId());
	}

	public void removeLab(long id) {
		em.remove(em.merge(findLab(id)));
		System.out.println("removeLab with id=" + id);
	}
	
	public Collection<Lab> getAllLabs(){
		Collection<Lab> labsLazy = em.createQuery("Select l from Lab l", Lab.class).getResultList();
		Collection<Lab> labs = new ArrayList<Lab>();
		for(Lab l : labsLazy) {
			labs.add(this.findLab(l.getLabId()));
		}
		return labs;
	}
	
	// DAO EVENT
	public Event addEvent(Event e) {
		em.persist(e);
		System.out.println("addEvent witdh id=" + e.getEventId());
		return e;
	}

	public Event findEvent(long id) {
		Event e = em.find(Event.class, id);
		System.out.println("findEvent with id=" + e.getEventId());
		//loading lazy all the event lists
		if(e.getAttendees().size()!=0)
			System.out.println("number of attendees: " + e.getAttendees().size());
		if(e.getSpeakers().size()!=0)
			System.out.println("number of speakers: " + e.getSpeakers().size());
		return e;
	}
	
	public void updateEvent(Event e) {
		em.merge(e);
		System.out.println("updateEvent with id=" + e.getEventId());
	}

	public void removeEvent(long id) {
		em.remove(em.merge(findEvent(id)));
		System.out.println("removeEvent with id=" + id);
	}
	
	public Collection<Event> getAllEvents(){
		Collection<Event> eventsLazy = em.createQuery("Select e from Event e", Event.class).getResultList();
		Collection<Event> events = new ArrayList<Event>();
		for(Event e : eventsLazy) {
			events.add(this.findEvent(e.getEventId()));
		}
		return events;
	}
	
	//USER
	public User authUser(User user) {
		System.out.println("DAO AUTH user:"+user);
		Query query = em.createQuery("SELECT r.researcherId FROM Researcher AS r WHERE email=?1 AND password =?2").setParameter(1, user.getEmail()).setParameter(2, user.getPassword());
		if(query.getResultList().isEmpty())
			return user;
		System.out.println(query.getResultList().get(0));
		long result = (long) query.getResultList().get(0);
		Query queryRole = em.createQuery("SELECT r.role FROM Researcher AS r WHERE researcherId=?1").setParameter(1,result);
		Role role = (Role) queryRole.getResultList().get(0);
		System.out.println(result);
		user.setId(result);
		user.setLoggedIn(true);
		user.setRole(role);
 		return user;
	}
	
	 
	/*
	
	public User authUser(User user) {
		System.out.println("DAO AUTH user:"+user);
		Query query = em.createQuery("SELECT p.personId FROM Person AS p WHERE username=?1 AND password =?2").setParameter(1, user.getUsername()).setParameter(2, user.getPassword());
		if(query.getResultList().isEmpty())
			return user;
		System.out.println(query.getResultList().get(0));
		long result = (long) query.getResultList().get(0);
		System.out.println(result);
		user.setId(result);
		user.setLoggedIn(true);
 		return user;
	}
	
	
	public void clearDatabase() {
		Query q1 = em.createQuery("DELETE FROM Person");
        Query q2 = em.createQuery("DELETE FROM Team");
        q1.executeUpdate();
        q2.executeUpdate();
	}
	 

	public Collection<Team> searchTeams(String search) {
		System.out.println(search);
		Collection<Team> teamsLazy = em.createQuery("Select t from Team t where t.teamName LIKE ?1", Team.class).setParameter(1, "%"+search+"%").getResultList();
		Collection<Team> teams = new ArrayList<Team>();
		for(Team t : teamsLazy) {
			teams.add(this.findTeam(t.getTeamId()));
		}
		return teams;
	}

	public boolean hasPerson(String username) {
		System.out.println("find " + username);
		Query query = em.createQuery("SELECT p.personId FROM Person AS p WHERE username=?1").setParameter(1, username);
		return !query.getResultList().isEmpty();
	}
	*/
}