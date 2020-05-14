package springapp.business;

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
	public boolean hasResearcher(String email) {
		return dao.hasResearcher(email);
	}

}
