package be.vdab.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "soorten")
public class Soort {
	// NAMED QUERIES
	public static final String FIND_BY_LAND = "Soort.findByLand";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "landid")
	private Land land;
	@Version
	private long versie;
	private String naam;

	// GETTERS
	public long getId() {
		return id;
	}

	public Land getLand() {
		return land;
	}

	public String getNaam() {
		return naam;
	}

}
