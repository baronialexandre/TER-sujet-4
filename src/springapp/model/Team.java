package springapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity(name = "Team")
public class Team implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long teamId;

	@Basic(optional = false)
	@Column(name = "teamName", length = 200,
	nullable = false, unique = true)
	private String teamName;

	@Version()
	private long version = 0;

	@Transient
	public static long updateCounter = 0;

	@OneToMany(
			cascade = { CascadeType.MERGE,  CascadeType.REMOVE }, //CascadeType.PERSIST enlevé
			fetch = FetchType.LAZY, mappedBy = "team")
	private Set<Person> members;

	public Team() {
		super();
	}

	public Team(String name) {
		super();
		this.teamName = name;
	}

	@PreUpdate
	public void beforeUpdate() {
		System.err.println("PreUpdate of " + this);
	}

	@PostUpdate
	public void afterUpdate() {
		System.err.println("PostUpdate of " + this);
		updateCounter++;
	}
	
	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public void setTeamName(String name) {
		this.teamName = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Set<Person> getMembers() {
		return members;
	}

	public void setPersons(Set<Person> members) {
		this.members = members;
	}

	public void addPerson(Person p) {
		if (members == null) {
			members = new HashSet<>();
		}
		p.setTeam(this);
		members.add(p);
	}

	@Override
	public String toString() {
		return "Team [id=" + teamId + ", name=" + teamName + ", version=" + version + ", members=" + members + "]";
	}


}