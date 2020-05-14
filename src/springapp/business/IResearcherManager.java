package springapp.business;

import springapp.model.Researcher;

public interface IResearcherManager {

	Researcher getResearcher(long id);
	void update(Researcher p);
	boolean hasResearcher(String email);

}
