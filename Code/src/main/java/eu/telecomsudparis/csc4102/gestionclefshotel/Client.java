package eu.telecomsudparis.csc4102.gestionclefshotel;

public class Client {
	int id;
	private String nom;
	private String prenom;

	public Client(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		assert invariant();
	}

	private boolean invariant() {
		return this.id != 0 && this.nom != null && this.prenom != null;
	}
}
