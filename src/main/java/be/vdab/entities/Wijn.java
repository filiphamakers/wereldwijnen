package be.vdab.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "wijnen")
public class Wijn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "soortid")
	@Version
	private long versie;
	@ManyToOne
	@JoinColumn(name = "soortid")
	private Soort soort;
	private int jaar;
	private int beoordeling;
	private BigDecimal prijs;
	private long inBestelling;

	public long getId() {
		return id;
	}

	public Soort getSoort() {
		return soort;
	}

	public int getJaar() {
		return jaar;
	}

	public int getBeoordeling() {
		return beoordeling;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public long getInBestelling() {
		return inBestelling;
	}

}
