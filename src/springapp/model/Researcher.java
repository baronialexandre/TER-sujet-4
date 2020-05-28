package springapp.model;


import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import springapp.model.utils.Role;

@Entity(name = "Researcher")
public class Researcher implements Serializable, Comparable<Researcher> {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long researcherId;
	
	@Basic(optional = false)
	@Email(message = "Email should be valid")
	@Column(name = "email", length = 200,
	nullable = false, unique = true)
	@Size(max = 200, message = "200 characters max")
	private String email;

	@Basic(optional = false)
	@Column(name = "first_name", length = 200,
	nullable = false)
	@Size(max = 200, message = "200 characters max")
	private String firstName;

	@Basic(optional = false)
	@Column(name = "last_name", length = 200,
	nullable = false)
	@Size(max = 200, message = "200 characters max")
	private String lastName;

	@Basic(optional = false)
	@Column(name = "website", length = 200,
	nullable = false)
	@Size(max = 200, message = "200 characters max")
	private String website;

	@Basic()
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Column(name = "birth_day")
	private Date birthDay;

	@Basic(optional = false)
	@Column(name = "password", length = 200,
	nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 200,
	nullable = false)
	private Role role;

	@Version()
	private long version = 0;

	@Transient
	public static long updateCounter = 0;
	
    @ManyToOne(optional = true)
    @JoinColumn(name = "lab")
    private Lab lab;
    
	@ManyToMany(mappedBy = "attendees") 
	private Set<Event> eventsAttending;
    
	@OneToMany(
			cascade = { CascadeType.MERGE,  CascadeType.REMOVE }, //CascadeType.PERSIST enlevé
			fetch = FetchType.LAZY, mappedBy = "organizer")
	private Set<Event> eventsOrganized;

	public Researcher() {
		super();
	}

	public Researcher(String email, String firstName, String lastName,  String website, Date birthDay, String password, Role role) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.website = website;
		this.birthDay = birthDay;
		this.password = password;
		this.role = role;
		this.lab = null;
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

	public long getResearcherId() {
		return researcherId;
	}

	public void setResearcherId(long researcherId) {
		this.researcherId = researcherId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Lab getLab() {
		return lab;
	}

	public void setLab(Lab lab) {
		this.lab = lab;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Event> getEventsAttending() {
		return eventsAttending;
	}

	public void setEventsAttending(Set<Event> eventsAttending) {
		this.eventsAttending = eventsAttending;
	}

	public void addAttendingEvent(Event event) {
		if (eventsAttending == null) {
			eventsAttending = new HashSet<>();
		}
		eventsAttending.add(event);
	}

	public Set<Event> getEventsOrganized() {
		return eventsOrganized;
	}

	public void setEventsOrganized(Set<Event> eventsOrganized) {
		this.eventsOrganized = eventsOrganized;
	}
	
	public void addOrganizedEvent(Event event) {
		if (eventsOrganized == null) {
			eventsOrganized = new HashSet<>();
		}
		eventsOrganized.add(event);
	}

	@Override
	public String toString() {
		return "Researcher [researcherId=" + researcherId + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", website=" + website + ", birthDay=" + birthDay + ", password="
				+ password + ", role=" + role + ", version=" + version + "]";
	}

	@Override
	public int compareTo(Researcher o2) {
		if(this.getLastName().compareTo(o2.getLastName()) == 0)
			return this.getFirstName().compareTo(o2.getFirstName());
		return this.getLastName().compareTo(o2.getLastName());
	}



}