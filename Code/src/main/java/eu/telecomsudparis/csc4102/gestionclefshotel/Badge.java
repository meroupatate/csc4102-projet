package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

import java.util.Objects;

public class Badge {

	private int id;
	private byte[] premiereClef;
	private byte[] secondeClef;

	public Badge(int id) throws ChaineDeCaracteresNullOuVide {
		if (id == 0) {
			throw new ChaineDeCaracteresNullOuVide("identifiant nul non autorisé");
		}
		this.id = id;
		assert invariant();
	}

	public void inscrireClefs(byte[] premiereClef, byte[] secondeClef) {
		this.premiereClef = premiereClef;
		this.secondeClef = secondeClef;
		assert invariant();
	}

	public void effacerClefs() {
		this.premiereClef = null;
		this.secondeClef = null;
		assert invariant();
	}

	private boolean invariant() {
		return this.id != 0
				&& ((this.premiereClef != null && this.secondeClef != null) || (this.premiereClef == null && this.secondeClef == null));
	}

	public int getId() {
		return this.id;
	}

	public byte[] getPremiereClef() {
		return this.premiereClef;
	}

	public byte[] getSecondeClef() {
		return this.secondeClef;
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
		if (!(obj instanceof Badge)) {
			return false;
		}
		Badge other = (Badge) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Badge [id=" + id + "]";
	}
}
