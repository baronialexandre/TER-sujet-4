package springapp.business;

import springapp.model.Person;

public interface IPersonManager {

	Person getPerson(long id);
	void update(Person p);
	boolean hasPerson(String username);

}
