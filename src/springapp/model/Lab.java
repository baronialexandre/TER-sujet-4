package springapp.model;

import java.io.Serializable;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.OrderBy;

@Entity(name = "Lab")
public class Lab implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long labId;

	@Basic(optional = false)
	@Column(name = "labName", length = 200, nullable = false, unique = true)
	private String labName;

	@Version()
	private long version = 0;

	@Transient
	public static long updateCounter = 0;

	@OneToMany(cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "lab")
	@OrderBy(clause = "last_name ASC")
	private SortedSet<Researcher> researchers;

	public Lab() {
		super();
	}

	public Lab(String labName) {
		super();
		this.labName = labName;
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

	public long getLabId() {
		return labId;
	}

	public void setLabId(long labId) {
		this.labId = labId;
	}

	public void setLabName(String name) {
		this.labName = name;
	}

	public String getLabName() {
		return labName;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Set<Researcher> getResearchers() {
		return researchers;
	}

	public void setResearchers(SortedSet<Researcher> researchers) {
		this.researchers = researchers;
	}

	public void addResearcher(Researcher p) {
		if (researchers == null) {
			researchers = new TreeSet<Researcher>();
		}
		p.setLab(this);
		researchers.add(p);
	}

	@Override
	public String toString() {
		return "Lab [id=" + labId + ", name=" + labName + ", version=" + version + ", researchers=" + researchers + "]";
	}

}