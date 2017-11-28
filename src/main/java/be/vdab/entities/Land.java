package be.vdab.entities;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "landen")
public class Land {
	//NAMED QUERIES
	public static final String FIND_ALL = "Land.findAll";
	
	@Id
	private long id;
	@Version
	private long versie;
	private String naam;
	@OneToMany
	@JoinColumn(name = "landid")
	private Set<Soort> soorten;
	
	//GETTERS
	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Set<Soort> getSoorten() {
		return Collections.unmodifiableSet(soorten);
	}

}
