package springapp.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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

    @Override
    public Collection<Lab> findAll() {
        return dao.getAllLabs();
    }

    @Override
	public void update(Lab lab) {
		dao.updateLab(lab);
	}
    
    @Override
	public void add(Lab lab) {
		dao.addLab(lab);
	}

	@Override
	public Lab find(long id) {
		return null;
	}

	public List<String> getAllLabNames() {
		List<Lab> allLabs = new ArrayList<Lab>(findAll());
		List<String> allLabNames = new ArrayList<>();
		for(Lab lab : allLabs)
			allLabNames.add(lab.getLabName());
		return allLabNames;
	}

	public Lab findByName(String name) {
		return dao.findLab(name);
	}

}