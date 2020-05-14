package springapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import springapp.model.utils.EventType;
import springapp.model.utils.StringListConverter;

@Entity(name = "Event")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long eventId;

	@Basic(optional = false)
	@Column(name = "eventName", length = 200,
	nullable = false, unique = true)
	private String eventName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "email", length = 200,
	nullable = false)
	private EventType type;

	@Basic(optional = false)
	@Column(name = "location", length = 200,
	nullable = false)
	private String location;
	
	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Column(name = "begin_date")
	private Date beginDate;
	
	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Column(name = "end_date")
	private Date endDate;

	@Basic(optional = false)
	@Column(name = "description", length = 5000,
	nullable = false)
	private String description;
	
	@Convert(converter = StringListConverter.class)
	@Column(name = "speakers")
	private List<String> speakers;
	
	@Basic(optional = false)
	@Column(name = "fee", nullable = false)
	private Float fee;
	
    @ManyToOne(optional = true)
    @JoinColumn(name = "organizer")
    private Researcher organizer;
    
	@Basic(optional = false)
	@Column(name = "attendee_cap", nullable = false)
	private Long attendeeCap;
	
	@ManyToMany
	@JoinTable(name = "attendees")
	private Set<Researcher> attendees;

	@Version()
	private long version = 0;

	@Transient
	public static long updateCounter = 0;
	

	public Event() {
		super();
	}

	public Event(String eventName, EventType type, String location, Date beginDate, Date endDate, String description,
			List<String> speakers, Float fee, Long attendeeCap) {
		super();
		this.eventName = eventName;
		this.type = type;
		this.location = location;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.description = description;
		this.speakers = speakers;
		this.fee = fee;
		this.attendeeCap = attendeeCap;
		//this.organizer = organizer;
		//organizer add org event?
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<String> speakers) {
		this.speakers = speakers;
	}

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	public Researcher getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Researcher organizer) {
		this.organizer = organizer;
	}
	
	public void addOrganizer(Researcher organizer) {
		organizer.addOrganizedEvent(this);
		this.organizer = organizer;
	}

	public Long getAttendeeCap() {
		return attendeeCap;
	}

	public void setAttendeeCap(Long attendeeCap) {
		this.attendeeCap = attendeeCap;
	}

	public Set<Researcher> getAttendees() {
		return attendees;
	}

	public void setAttendees(Set<Researcher> attendees) {
		this.attendees = attendees;
	}
	
	public void addAttendee(Researcher p) {
		if (attendees == null) {
			attendees = new HashSet<>();
		}
		p.addAttendingEvent(this);
		attendees.add(p);
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", type=" + type + ", location=" + location
				+ ", beginDate=" + beginDate + ", endDate=" + endDate + ", description=" + description + ", speakers="
				+ speakers + ", fee=" + fee + ", organizer=" + organizer.getEmail() + ", attendeeCap=" + attendeeCap
				+ ", attendees=" + attendees.size() + ", version=" + version + "]";
	}


}