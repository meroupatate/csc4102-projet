package eu.telecomsudparis.csc4102.gestionclefshotel;

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
}
