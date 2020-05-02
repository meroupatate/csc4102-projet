package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Objects;

/**
 * Représente un client.
 */
public class Client {
	/**
	 * Identifiant du client.
	 */
	private int id;
	/**
	 * Nom du client.
	 */
	private String nom;
	/**
	 * Prénom du client.
	 */
	private String prenom;

	/**
	 * Constructeur de la classe Client avec un identifiant, un nom et un prénom.
	 * @param id identifiant du client à créer
	 * @param nom nom du client à créer
	 * @param prenom prénom du client à créer
	 */
	public Client(final int id, final String nom, final String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		assert invariant();
	}

	/**
	 * Invariant de la classe Client.
	 * @return boolean
	 */
	private boolean invariant() {
		return this.id != 0 && this.nom != null && this.prenom != null;
	}

	/**
	 * Permet d'accéder à l'attribut id.
	 * @return int
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Permet d'accéder à l'attribut nom.
	 * @return String
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Permet d'accéder à l'attribut prénom.
	 * @return String
	 */
	public String getPrenom() {
		return this.prenom;
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
