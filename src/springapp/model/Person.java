package springapp.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long personId;

	@Basic(optional = false)
	@Column(name = "username", length = 200,
	nullable = false, unique = true)
	@Size(max = 200, message = "200 characters max")
	private String username;

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
	@Email(message = "Email should be valid")
	@Column(name = "email", length = 200,
	nullable = false)
	@Size(max = 200, message = "200 characters max")
	private String email;

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

	@Version()
	private long version = 0;

	@Transient
	public static long updateCounter = 0;
	
    @ManyToOne(optional = true)
    @JoinColumn(name = "team")
    private Team team;

	public Person() {
		super();
	}

	public Person(String username, String firstName, String lastName, String email, String website, Date birthDay, String password) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.website = website;
		this.birthDay = birthDay;
		this.password = password;
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

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Person [id=" + personId + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", website=" + website + ", birthDay=" + birthDay + ", password=" + password
				+ ", version=" + version + "]";
	}

}