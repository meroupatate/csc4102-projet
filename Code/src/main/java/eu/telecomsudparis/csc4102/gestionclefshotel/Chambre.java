package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;
import eu.telecomsudparis.csc4102.gestionserrures.Serrure;

import java.util.Objects;
import java.util.Optional;

public class Chambre {
	private int id;
	private String graine;
	private int sel;
	private byte[] premiereClef;
	private byte[] secondeClef;
	private Badge badge;
	private Client client;

	public Chambre(int id, String graine, int sel) throws ProblemeDansGenerationClef {
		this.id = id;
		this.graine = graine;
		this.sel = sel;
		premiereClef = this.genereClef();
		secondeClef = this.genereClef();
		assert invariant();
	}

	private boolean invariant() {
		return id != 0 && graine != null && sel != 0 && premiereClef != null && secondeClef != null;
	}

	private byte[] genereClef() throws ProblemeDansGenerationClef {
		byte[] nouvelleClef = Util.genererUneNouvelleClef(this.graine, String.format("%010d%n", this.sel));
		this.sel++;
		return nouvelleClef;
	}

	public byte[] getPremiereClef() {
		return this.premiereClef;
	}

	public byte[] getSecondeClef() {
		return this.secondeClef;
	}

	public String getGraine() {
		return this.graine;
	}

	public int getSel() {
		return this.sel;
	}

	public Badge getBadge() {
		return this.badge;
	}

	public Client getClient() {
		return this.client;
	}

	public void associerClient(Client client) {
		this.client = client;
	}

	public void associerBadge(Badge badge) {
		this.badge = badge;
	}

	public void liberer() {
		this.badge = null;
		this.client = null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Chambre)) {
			return false;
		}
		Chambre other = (Chambre) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Chambre [graine=" + graine + ", sel=" + sel + "]";
	}
}
