package be.vdab.valueobjects;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Wijn;

@Embeddable
public class Bestelbonlijn {
	private long aantal;
	private BigDecimal prijs;
	@ManyToOne
	@JoinColumn(name="wijnid")
	private Wijn wijn;

	protected Bestelbonlijn() {
	}

	public Bestelbonlijn(Wijn wijn, long aantal) {
		if (wijn == null) {
			throw new IllegalArgumentException("argument wijn mag niet null zijn");
		}
		if (aantal <= 0) {
			throw new IllegalArgumentException("argument aantal moet groter zijn dan 0");
		}
		this.wijn = wijn;
		this.aantal = aantal;
		prijs = wijn.getPrijs();
	}

	public BigDecimal getPrijs() {
		return prijs;
	}
	
	public long getAantal() {
		return aantal;
	}

	public Wijn getWijn() {
		return wijn;
	}
	
	public BigDecimal getSubtotaal() {
		return prijs.multiply(new BigDecimal(aantal));
	}

}
