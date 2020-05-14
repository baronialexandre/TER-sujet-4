package springapp.business;

import springapp.model.Researcher;

public interface IResearcherManager {

	Researcher getPerson(long id);
	void update(Researcher p);
	boolean hasResearcher(String email);

}
