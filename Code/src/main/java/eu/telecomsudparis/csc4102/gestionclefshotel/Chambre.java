package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;

import java.util.Objects;

/**
 * Représente une chambre.
 */
public class Chambre {
	/**
	 * Identifiant de la chambre.
	 */
	private int id;
	/**
	 * Graine de la chambre.
	 */
	private String graine;
	/**
	 * Sel de la chambre.
	 */
	private int sel;
	/**
	 * Première clef de la chambre.
	 */
	private byte[] premiereClef;
	/**
	 * Seconde clef de la chambre.
	 */
	private byte[] secondeClef;
	/**
	 * Badge associé à la chambre.
	 */
	private Badge badge;
	/**
	 * Client associé à la chambre.
 	 */
	private Client client;

	/**
	 * Constructeur d'un objet chambre avec un identifiant, une graine et un sel.
	 * @param id
	 * @param graine
	 * @param sel
	 * @throws ProblemeDansGenerationClef
	 */
	public Chambre(final int id, final String graine, final int sel) throws ProblemeDansGenerationClef {
		this.id = id;
		this.graine = graine;
		this.sel = sel;
		premiereClef = this.genereClef();
		secondeClef = this.genereClef();
		assert invariant();
	}

	/**
	 * Invariant de la classe Badge.
	 * @return boolean
	 */
	private boolean invariant() {
		return id != 0 && graine != null && sel != 0 && premiereClef != null && secondeClef != null
				&& ((badge != null && client != null) || (badge == null && client == null));
	}

	/**
	 * Génère une clef à partir de la graine et du sel de la chambre.
	 * Incrémente le sel après chaque génération.
	 * @return byte[]
	 * @throws ProblemeDansGenerationClef
	 */
	private byte[] genereClef() throws ProblemeDansGenerationClef {
		byte[] nouvelleClef = Util.genererUneNouvelleClef(this.graine, String.format("%010d%n", this.sel));
		this.sel++;
		return nouvelleClef;
	}

	/**
	 * Permet d'accéder à l'identifiant de la chambre.
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
	 * Permet d'accéder à la graine de la chambre.
	 * @return String
	 */
	public String getGraine() {
		return this.graine;
	}

	/**
	 * Permet d'accéder au sel de la chambre.
	 * @return int
	 */
	public int getSel() {
		return this.sel;
	}

	/**
	 * Permet d'accéder au badge associé à la chambre.
	 * @return Badge
	 */
	public Badge getBadge() {
		return this.badge;
	}

	/**
	 * Permet d'accéder au client associé à la chambre.
	 * @return Client
	 */
	public Client getClient() {
		return this.client;
	}

	/**
	 * Associe la chambre avec un client et le badge donné à ce client.
	 * @param client
	 * @param badge
	 */
	public void associerClientBadge(final Client client, final Badge badge) {
		this.client = client;
		this.badge = badge;
		assert invariant();
	}

	/**
	 * Libère la chambre.
	 * Met à null les attributs badge et client de la chambre.
	 */
	public void liberer() {
		this.badge = null;
		this.client = null;
		assert invariant();
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
		return "Chambre [graine=" + graine + ", sel=" + sel + ", client=" + client + ", badge=" + badge + "]";
	}
}
