package springapp.business;

import java.util.Collection;

import springapp.model.Event;

public interface IEventManager {

	Collection<Event> findAll();

	Collection<Event> findActive();
	
	Collection<Event> findAtYear(int year);

    void update(Event event);

    Event find(long id);

	void add(Event event);

	Collection<Event> findLast(int howMany);

	Collection<Event> findLastArchives(int howMany);
    
}
