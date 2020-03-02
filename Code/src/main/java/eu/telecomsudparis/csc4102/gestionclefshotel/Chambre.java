package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;

import java.util.Optional;

public class Chambre {
	int id;
	String graine;
	int sel;
	byte[] premiereClef;
	byte[] secondeClef;
	Badge badge;
	Client client;

	public Chambre(int id, String graine, int sel) throws ProblemeDansGenerationClef {
		this.id = id;
		this.graine = graine;
		this.sel = sel;
		premiereClef = Util.genererUneNouvelleClef(graine, String.format("%010d%n", this.sel));
		this.sel++;
		secondeClef = Util.genererUneNouvelleClef(graine, String.format("%010d%n", this.sel));
		assert invariant();
	}

	private boolean invariant() {
		return id != 0 && graine != null && sel != 0 && premiereClef != null && secondeClef != null;
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
}
