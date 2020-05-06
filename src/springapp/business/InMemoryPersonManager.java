package springapp.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.dao.Dao;
import springapp.model.Person;

@Service("personManager")
public class InMemoryPersonManager implements IPersonManager {

	@Autowired
	Dao dao;
	
	@Override
	public Person getPerson(long id) {
		return dao.findPerson(id);
	}

	@Override
	public void update(Person p) {
		dao.updatePerson(p);
	}

	@Override
	public boolean hasPerson(String username) {
		return dao.hasPerson(username);
	}

}
