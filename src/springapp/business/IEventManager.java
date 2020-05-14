package springapp.business;

import java.util.Collection;

import springapp.model.Event;

public interface IEventManager {

    Collection<Event> findAll();

    void save(Event event);

    Event find(long id);
    
}
