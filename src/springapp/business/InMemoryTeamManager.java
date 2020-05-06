package springapp.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import springapp.dao.Dao;
import springapp.model.Person;
import springapp.model.Team;

@Service("teamManager")
public class InMemoryTeamManager implements ITeamManager {

	@Autowired
	Dao dao;
	
	Random rnd = new Random();
    int maxId = 0;

    public InMemoryTeamManager() {
    }
    
    @PostConstruct
    public void init() {
    	TeamPersonGenerator gen = new TeamPersonGenerator();
    	for(Team t : gen.generateTeams(1)) {
    		dao.addTeam(t);
    		for (Person p : t.getMembers()) {
    			dao.addPerson(p);
    		}
    	}
    	ArrayList<Team> teams = new ArrayList<Team>(dao.getAllTeams());
		Faker faker = new Faker();
		Person admin = new Person("admin","ADMIN " + faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),faker.internet().url(),faker.date().birthday(),"admin");
    	teams.get(0).addPerson(admin);
		dao.addPerson(admin);
    	
    }

    @Override
    public Collection<Team> findAll() {
        return dao.getAllTeams();
    }

	@Override
	public void save(Team p) {
		return;
	}

	@Override
	public Team find(long id) {
		return null;
	}

	@Override
	public Collection<Team> search(String search) {
		return dao.searchTeams(search);
	}



}