package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Client)) {
			return false;
		}
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + "]";
	}
}
