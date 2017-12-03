package be.vdab.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.utils.StringUtils;
import be.vdab.valueobjects.Bestelbonlijn;

@Entity
@Table(name = "bestelbonnen")
public class Bestelbon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDateTime besteld;
	private String naam;
	private String straat;
	private String huisNr;
	private String postCode;
	private String gemeente;
	private int bestelwijze;
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bonid"))
	private List<Bestelbonlijn> bestelbonlijnen;
	@Version
	private long versie;

	protected Bestelbon() {
	}

	public Bestelbon(String naam, String straat, String huisNr, String postCode, String gemeente, int bestelwijze,
			List<Bestelbonlijn> bestelbonlijnen) {
		if (StringUtils.isEmptyOrNull(naam)) {
			throw new IllegalArgumentException();
		}
		if (StringUtils.isEmptyOrNull(straat)) {
			throw new IllegalArgumentException();
		}
		if (StringUtils.isEmptyOrNull(huisNr)) {
			throw new IllegalArgumentException();
		}
		if (StringUtils.isEmptyOrNull(postCode)) {
			throw new IllegalArgumentException();
		}
		if (StringUtils.isEmptyOrNull(gemeente)) {
			throw new IllegalArgumentException();
		}
		if (bestelwijze < 0 || bestelwijze > 1) {
			throw new IllegalArgumentException();
		}
		if (bestelbonlijnen == null) {
			throw new IllegalArgumentException();
		}
		this.naam = naam;
		this.straat = straat;
		this.huisNr = huisNr;
		this.postCode = postCode;
		this.gemeente = gemeente;
		this.bestelwijze = bestelwijze;
		this.bestelbonlijnen = bestelbonlijnen;
		besteld = LocalDateTime.now();
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getBesteld() {
		return besteld;
	}

	public String getNaam() {
		return naam;
	}

	public String getStraat() {
		return straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public int getBestelwijze() {
		return bestelwijze;
	}

	public List<Bestelbonlijn> getBestelbonlijnen() {
		return Collections.unmodifiableList(bestelbonlijnen);
	}

}
