package springapp.business;

import java.util.Collection;
import java.util.Map;

import springapp.model.Lab;

public interface ILabManager {

	Collection<Lab> findAll();

	void update(Lab lab);

	Lab find(long id);

	Map<Long, String> getLabNameMap();

	Lab findByName(String name);

	void add(Lab lab);

	boolean delete(long id);

}
