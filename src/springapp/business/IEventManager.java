package springapp.business;

import java.util.Collection;

import springapp.model.Event;

public interface IEventManager {

	Collection<Event> findAll();

	Collection<Event> findActive();
	
	Collection<Event> findAtYear(int year);

    void save(Event event);

    Event find(long id);
    
}
