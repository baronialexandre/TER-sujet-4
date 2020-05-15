package springapp.business;

import java.util.Collection;
import java.util.List;

import springapp.model.Lab;

public interface ILabManager {

    Collection<Lab> findAll();

    void update(Lab lab);

    Lab find(long id);
    
    List<String> getAllLabNames();
    
    Lab findByName(String name);

	void add(Lab lab);
}
