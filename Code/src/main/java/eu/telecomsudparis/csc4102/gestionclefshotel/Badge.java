package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;

import java.util.Objects;

/**
 * Représente un badge.
 */
public class Badge {

	/**
	 * Identifiant de la classe.
	 */
	private int id;
	/**
	 * Première clef du badge.
	 */
	private byte[] premiereClef;
	/**
	 * Seconde clef du badge.
	 */
	private byte[] secondeClef;
	/**
	 * Indique si le badge est perdu.
	 */
	private boolean perdu;

	/**
	 * Constructeur de la classe Badge avec un identifiant non nul.
	 * @param id identifiant du badge à créer
	 * @throws ChaineDeCaracteresNullOuVide quand l'identifiant est nul
	 */
	public Badge(final int id) throws ChaineDeCaracteresNullOuVide {
		if (id == 0) {
			throw new ChaineDeCaracteresNullOuVide("identifiant nul non autorisé");
		}
		this.id = id;
		this.perdu = false;
		assert invariant();
	}

	/**
	 * Permet d'inscrire deux clefs dans le badge.
	 * @param premiereClef première clef à inscrire
	 * @param secondeClef seconde clef à inscrire
	 */
	public void inscrireClefs(final byte[] premiereClef, final byte[] secondeClef) {
		this.premiereClef = premiereClef.clone();
		this.secondeClef = secondeClef.clone();
		assert invariant();
	}

	/**
	 * Permet d'effacer les clefs sur le badge.
	 */
	public void effacerClefs() {
		this.premiereClef = null;
		this.secondeClef = null;
		assert invariant();
	}

	/**
	 * Invariant de la classe Badge.
	 * @return boolean
	 */
	private boolean invariant() {
		return this.id != 0
				&& ((this.premiereClef != null && this.secondeClef != null) || (this.premiereClef == null && this.secondeClef == null));
	}

	/**
	 * Permet d'accéder à l'attribut id.
	 * @return int
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Permet d'accéder à l'attribut premiereClef.
	 * @return byte[]
	 */
	public byte[] getPremiereClef() {
		if (this.premiereClef == null) {
			return null;
		}
		return this.premiereClef.clone();
	}

	/**
	 * Permet d'accéder à l'attribut secondeClef.
	 * @return byte[]
	 */
	public byte[] getSecondeClef() {
		if (this.secondeClef == null) {
			return null;
		}
		return this.secondeClef.clone();
	}

	/**
	 * Déclare le badge comme perdu.
	 */
	public void declarerPerte() {
		this.perdu = true;
	}

	/**
	 * Vérifie si le badge est perdu.
	 * @return boolean true si le badge est perdu et false sinon
	 */
	public boolean estPerdu() {
		return this.perdu;
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
