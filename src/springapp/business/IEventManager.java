package springapp.business;

import java.util.Collection;

import springapp.model.Event;

public interface IEventManager {

	Collection<Event> findAll();

	Collection<Event> findActive();

	Collection<Event> findArchiveAtYear(int year);

	void update(Event event);

	Event find(long id);

	void add(Event event);

}
