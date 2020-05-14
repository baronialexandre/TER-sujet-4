package springapp.business;

import java.util.Collection;
import java.util.Random;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.dao.Dao;
import springapp.model.Lab;

@Service("labManager")
public class InMemoryLabManager implements ILabManager {

	@Autowired
	Dao dao;
	
	Random rnd = new Random();
    int maxId = 0;

    public InMemoryLabManager() {
    }
    
    @PostConstruct
    public void init() {
    	EventLabResearcherGenerator.generateEventsLabsResearchers(dao, 10, 5);
    }

    @Override
    public Collection<Lab> findAll() {
        return dao.getAllLabs();
    }

	@Override
	public void save(Lab lab) {
		return;
	}

	@Override
	public Lab find(long id) {
		return null;
	}

}