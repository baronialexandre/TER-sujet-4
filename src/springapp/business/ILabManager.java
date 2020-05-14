package springapp.business;

import java.util.Collection;

import springapp.model.Lab;

public interface ILabManager {

    Collection<Lab> findAll();

    void save(Lab lab);

    Lab find(long id);


}
