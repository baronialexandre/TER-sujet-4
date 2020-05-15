package springapp.business;

import java.util.Collection;
import java.util.Random;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.dao.Dao;
import springapp.model.Event;

@Service("eventManager")
public class InMemoryEventManager implements IEventManager {

	@Autowired
	Dao dao;
	
	Random rnd = new Random();
    int maxId = 0;

    public InMemoryEventManager() {
    }
    
    @PostConstruct
    public void init() {
    	if(dao.getAllEvents().isEmpty())
    		EventLabResearcherGenerator.generateEventsLabsResearchers(dao, 10, 5);
    }

    @Override
    public Collection<Event> findAll() {
        return dao.getAllEvents();
    }

	@Override
	public void save(Event event) {
		dao.updateEvent(event);
	}

	@Override
	public Event find(long id) {
		return dao.findEvent(id);
	}

	@Override
	public Collection<Event> findActive() {
		return dao.getActiveEvents();
	}

	@Override
	public Collection<Event> findAtYear(int year) {
		return dao.getEventsYear(year);
	}

}