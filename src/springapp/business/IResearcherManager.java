package springapp.business;

import java.util.Collection;

import springapp.model.Researcher;

public interface IResearcherManager {

	Researcher getResearcher(long id);

	void update(Researcher p);

	boolean hasResearcher(String email);

	void add(Researcher r);

	Collection<Researcher> findResearchers(String firstOrLastname);

	Collection<Researcher> getResearchersWithNoLab();
}
