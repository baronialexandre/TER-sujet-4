package springapp.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
		return dao.findLab(id);
	}

	@Override
	public boolean delete(long id) {
		try {
			dao.removeLab(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Map<Long, String> getLabNameMap() {
		List<Lab> allLabs = new ArrayList<Lab>(findAll());
		Map<Long, String> labMap = new LinkedHashMap<>();
		for (Lab lab : allLabs)
			labMap.put(lab.getLabId(), lab.getLabName());
		return labMap;
	}

	public Lab findByName(String name) {
		return dao.findLab(name);
	}

}