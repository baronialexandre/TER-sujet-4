package springapp.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import springapp.model.Person;
import springapp.model.Team;

public class TeamPersonGenerator {
	
	Random rnd = new Random();
	
	
	//public Person(String username, String firstName, String lastName, String email, String website, Date birthDay, String password) 
	public Team fillTeam(Team team) {
		for (int i = 0; i < rnd.nextInt(18) + 2; i++) {
			Faker faker = new Faker(new Locale("FR"));
			team.addPerson(new Person(faker.name().username()+rnd.nextInt(1000),faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),faker.internet().url(),faker.date().birthday(),faker.internet().password()));
		}
		return team;
	}
	
	//	public Team(String name) 
	public List<Team> generateTeams(int numberOfTeams){
		List<Team> teams = new ArrayList<Team>();
		for (int i = 0; i < numberOfTeams; i++) {
			Faker faker = new Faker(new Locale("FR"));
			teams.add(fillTeam(new Team(faker.animal().name() + "-" + faker.color().name() + "-" + rnd.nextInt(1000))));
		}
		return teams;
	}
	

}
