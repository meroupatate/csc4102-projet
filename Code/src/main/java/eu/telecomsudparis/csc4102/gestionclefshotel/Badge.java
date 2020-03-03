package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Objects;

public class Badge {

	int id;
	byte[] premiereClef;
	byte[] secondeClef;

	public Badge(int id) {
		this.id = id;
		assert invariant();
	}

	public void inscrireClefs(byte[] premiereClef, byte[] secondeClef) {
		this.premiereClef = premiereClef;
		this.secondeClef = secondeClef;
	}

	public void effacerClefs() {
		this.premiereClef = null;
		this.secondeClef = null;
	}

	private boolean invariant() {
		return this.id != 0;
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
