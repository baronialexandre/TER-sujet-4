package springapp.business;

import java.util.Collection;

import springapp.model.Team;

public interface ITeamManager {

    Collection<Team> findAll();

    void save(Team p);

    Team find(long id);

	Collection<Team> search(String search);

}
