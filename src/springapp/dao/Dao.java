package springapp.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;



import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springapp.model.Team;
import springapp.model.User;
import springapp.model.Person;

@Service
@Repository
@Transactional
public class Dao {

	
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	// DAO GROUP
	public Team addTeam(Team g) {
		em.persist(g);
		System.out.println("addTeam witdh id=" + g.getTeamId());
		return g;
	}

	public Team findTeam(long id) {
		Team g = em.find(Team.class, id);
		System.out.println("findTeam with id=" + g.getTeamId());
		System.out.println("Number of people=" + g.getMembers().size());
		return g;
	}
	
	public void updateTeam(Team g) {
		em.merge(g);
		System.out.println("updateTeam with id=" + g.getTeamId());
	}

	public void removeTeam(long id) {
		em.remove(em.merge(findTeam(id)));
		System.out.println("removeTeam with id=" + id);
	}
	
	public Collection<Team> getAllTeams(){
		Collection<Team> teamsLazy = em.createQuery("Select t from Team t", Team.class).getResultList();
		Collection<Team> teams = new ArrayList<Team>();
		for(Team t : teamsLazy) {
			teams.add(this.findTeam(t.getTeamId()));
		}
		return teams;
	}
	
	
	// DAO PERSONNE
	
	public Person addPerson(Person p) {
		em.persist(p);
		System.out.println("addPerson witdh id=" + p.getPersonId());
		return p;
	}
	
	public Person findPerson(long id){
		Person p = em.find(Person.class, id);
		System.out.println("findPerson with id=" + p.getPersonId());
		return p;
	}

	public void updatePerson(Person p) {
		em.merge(p);
		System.out.println("updatePerson with id=" + p.getPersonId());
	}

	public void removePerson(long id) {
		em.remove(em.merge(findPerson(id)));
		System.out.println("removePerson with id=" + id);
	}
	
	
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
	
	/*
	public void clearDatabase() {
		Query q1 = em.createQuery("DELETE FROM Person");
        Query q2 = em.createQuery("DELETE FROM Team");
        q1.executeUpdate();
        q2.executeUpdate();
	}
	 */

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
	
}