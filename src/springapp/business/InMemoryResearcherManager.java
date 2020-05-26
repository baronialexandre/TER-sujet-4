package springapp.business;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.dao.Dao;
import springapp.model.Researcher;

@Service("researcherManager")
public class InMemoryResearcherManager implements IResearcherManager {

	@Autowired
	Dao dao;
	
	@Override
	public Researcher getResearcher(long id) {
		return dao.findResearcher(id);
	}

	@Override
	public void update(Researcher r) {
		dao.updateResearcher(r);
	}
	
	@Override
	public void add(Researcher r) {
		dao.addResearcher(r);
	}

	@Override
	public boolean hasResearcher(String email) {
		return dao.hasResearcher(email);
	}
	
	@Override
	public Collection<Researcher> findResearchers(String firstOrLastname) {
		return dao.searchResearchers(firstOrLastname);
	}
	
	@Override
	public Collection<Researcher> getResearchersWithNoLab() {
		return dao.getResearchersWithNoLab();
	}

}
