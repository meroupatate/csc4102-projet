package eu.telecomsudparis.csc4102.gestionclefshotel;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.SubmissionPublisher;

/**
 * Cette classe définit la façade du système de gestion des clefs de l'hôtel.
 * 
 * @author Denis Conan
 */
public final class GestionClefsHotel {

	/**
	 * Collection de clients du système.
	 */
	private Map<Integer, Client> clients;
	/**
	 * Collection de badges du système.
	 */
	private Map<Integer, Badge> badges;
	/**
	 * Collection de chambres du système.
	 */
	private Map<Integer, Chambre> chambres;
	/**
	 * Producteur.
	 */
	private SubmissionPublisher<String> producteur;
	/**
	 * Instance accessible de la façade GestionClefsHotel.
	 */
	private static GestionClefsHotel instance;

	/**
	 * Constructeur de la façade du système de gestion des clefs de l'hotel.
	 */
	private GestionClefsHotel() {
		producteur = new SubmissionPublisher<String>();
		clients = new HashMap<>();
		badges = new HashMap<>();
		chambres = new HashMap<>();
	}

	/**
	 * Crée une instance si aucune instance de la classe GestionClefsHotel n'existe.
	 * @return GestionClefsHotel
	 */
	public static synchronized GestionClefsHotel getInstance() {
		if (instance == null) {
			instance = new GestionClefsHotel();
		}
		return instance;
	}

	/**
	 * Permet de réinitialiser le singleton.
	 */
	public void reset() {
		chambres.clear();
		clients.clear();
	    badges.clear();
	}

	/**
	 * teste si l'invariant est vérifié.
	 *
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return true;
	}

	/**
	 * Crée une chambre dans le système avec un identifiant, une graine et un sel.
	 *
	 * @param id     identifiant de la chambre à créer
	 * @param graine graine de la chambre à créer
	 * @param sel    sel de la chambre à créer
	 * @throws OperationImpossible si la chambre n'existe pas.
	 */
	public void creerChambre(final int id, final String graine, final int sel) throws OperationImpossible {
		if (id == 0) {
			throw new ChaineDeCaracteresNullOuVide("identifiant nul non autorisé");
		} else if (graine == null || graine.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("graine null ou vide non autorisée");
		} else if (chercherChambre(id).isPresent()) {
			throw new OperationImpossible("impossible de créer la chambre avec un identifiant déjà utilisé");
		}
		chambres.put(id, new Chambre(id, graine, sel));
		byte[] pClef = chambres.get(id).getPremiereClef();
		byte[] sClef = chambres.get(id).getSecondeClef();
		for (Chambre c : chambres.values()) {
			if (pClef == c.getPremiereClef() || pClef == c.getSecondeClef() || sClef == c.getPremiereClef() || sClef == c.getSecondeClef()) {
			    producteur.submit("doublon de clés avec la chambre " + c.getId() + " détecté lors de la création de la chambre " + id);
            }
		}
	}

	/**
	 * Crée un client dans le système avec un identifiant, un nom et un prénom.
	 *
	 * @param id     identifiant du client à créer
	 * @param nom    nom du client à créer
	 * @param prenom prénom du client à créer
	 * @throws OperationImpossible erreur dans la création du client
	 */
	public void creerClient(final int id, final String nom, final String prenom) throws OperationImpossible {
		if (id == 0) {
			throw new ChaineDeCaracteresNullOuVide("identifiant nul non autorisé");
		} else if (nom == null || prenom == null || nom.equals("") || prenom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("nom ou prénom null ou vide non autorisé");
		} else if (chercherClient(id).isPresent()) {
			throw new OperationImpossible("impossible de créer un client avec un identifiant déjà utilisé");
		}
		clients.put(id, new Client(id, nom, prenom));
	}

	/**
	 * Crée un badge dans le système avec un identifiant.
	 *
	 * @param id identifiant du badge à créer
	 * @throws OperationImpossible erreur dans la création du badge
	 */
	public void creerBadge(final int id) throws OperationImpossible {
		if (id == 0) {
			throw new ChaineDeCaracteresNullOuVide("identifiant nul non autorisé");
		} else if (chercherBadge(id).isPresent()) {
			throw new OperationImpossible("impossible de créer un badge avec un identifiant déjà utilisé");
		}
		badges.put(id, new Badge(id));
	}

	/**
	 * Recherche une chambre dans le système à partir d'un identifiant.
	 *
	 * @param id identifiant de la chambre à chercher
	 * @return Optional qui contient la chambre.
	 * @throws OperationImpossible quand l'identifiant recherché est nul
	 */
	public Optional<Chambre> chercherChambre(final int id) throws OperationImpossible {
		if (id == 0) {
			throw new OperationImpossible("impossible de rechercher une chambre avec un identifiant nul");
		}
		return Optional.ofNullable(chambres.get(id));
	}

	/**
	 * Recherche un client dans le système à partir d'un identifiant.
	 *
	 * @param id identifiant du client à chercher
	 * @return Optional qui contient le client.
	 * @throws OperationImpossible quand l'identifiant recherché est nul
	 */
	public Optional<Client> chercherClient(final int id) throws OperationImpossible {
		if (id == 0) {
			throw new OperationImpossible("impossible de rechercher un client avec un identifiant nul");
		}
		return Optional.ofNullable(clients.get(id));
	}

	/**
	 * Recherche un badge dans le système à partir d'un identifiant.
	 *
	 * @param id identifiant du badge à chercher
	 * @return Optional qui contient le badge.
	 * @throws OperationImpossible quand l'identifiant recherché est nul
	 */
	public Optional<Badge> chercherBadge(final int id) throws OperationImpossible {
		if (id == 0) {
			throw new OperationImpossible("impossible de rechercher un badge avec un identifiant nul");
		}
		return Optional.ofNullable(badges.get(id));
	}

	/**
	 * Enregistre l'occupation d'une chambre par un client.
	 * Prend en arguments les identifiants de la chambre, du client et du badge.
	 * Associe le client et le badge à la chambre.
	 *
	 * @param idChambre identifiant de la chambre à occuper
	 * @param idClient  identifiant du client à enregistrer
	 * @param idBadge   identifiant du badge à utiliser
	 * @throws OperationImpossible erreur dans l'enregistrement du client
	 */
	public void enregistrerOccupationChambreClient(final int idChambre, final int idClient, final int idBadge) throws OperationImpossible {
		Optional<Chambre> chambre = chercherChambre(idChambre);
		Optional<Client> client = chercherClient(idClient);
		Optional<Badge> badge = chercherBadge(idBadge);
		if (!client.isPresent()) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre par un client inexistant");
		} else if (client.get().getNom() == null || client.get().getPrenom() == null || client.get().getPrenom().equals("") || client.get().getNom().equals("")) {
			throw new ChaineDeCaracteresNullOuVide("nom ou prénom du client null ou vide non autorisé");
		} else if (!chambre.isPresent()) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre inexistante");
		} else if (chambre.get().getGraine() == null) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre aux attributs invalides");
		} else if (chambre.get().getClient() != null || chambre.get().getBadge() != null) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre déjà occupée");
		} else if (!badge.isPresent()) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre avec un badge inexistant");
		}
		chambre.get().associerClientBadge(client.get(), badge.get());
		byte[] nouvelleClef = Util.genererUneNouvelleClef(chambre.get().getGraine(), String.format("%010d%n", chambre.get().getSel()));
		badge.get().inscrireClefs(chambre.get().getSecondeClef(), nouvelleClef);
		for (Chambre c : chambres.values()) {
			if (nouvelleClef == c.getPremiereClef() || nouvelleClef == c.getSecondeClef()) {
				producteur.submit("doublon de clés avec la chambre " + c.getId() + " détecté lors de la génération d'une nouvelle clé pour le badge " + idBadge);
			}
		}
	}

	/**
	 * Libère une chambre du système.
	 *
	 * @param idChambre identifiant de la chambre à libérer
	 * @throws OperationImpossible erreur dans la libération de la chambre
	 */
	public void libererChambre(final int idChambre) throws OperationImpossible {
		Optional<Chambre> chambre = chercherChambre(idChambre);
		if (!chambre.isPresent()) {
			throw new OperationImpossible("impossible de libérer une chambre inexistante");
		}
		Optional<Badge> badge = Optional.ofNullable(chambre.get().getBadge());
		Optional<Client> client = Optional.ofNullable(chambre.get().getClient());
		if (!badge.isPresent() || !client.isPresent()) {
			throw new OperationImpossible("impossible de libérer une chambre inoccupée");
		}
		chambre.get().liberer();
		badge.get().effacerClefs();
	}

	/**
	 * Déclare la perte d'un badge sans remplacement.
	 *
	 * @param idBadge identifiant du badge perdu
	 * @throws OperationImpossible erreur dans la déclaration de perte
	 */
	public void declarerPerteBadgeSansRemplacement(final int idBadge) throws OperationImpossible {
		Optional<Badge> badge = chercherBadge(idBadge);
		Optional<Chambre> chambre = chercherChambreBadge(idBadge);
		if (!badge.isPresent()) {
			throw new OperationImpossible("impossible de déclarer perdu un badge inexistant");
		}
		if (chambre.isPresent()) {
		    libererChambre(chambre.get().getId());
		}
		badge.get().declarerPerte();
	}

	/**
	 * Déclare la perte d'un badge avec remplacement.
	 * @param idBadge identifiant du badge perdu
	 * @param idNouveauBadge identifiant du nouveau badge à attribuer
	 * @throws OperationImpossible erreur dans la déclaration de perte
	 */
	public void declarerPerteBadgeAvecRemplacement(final int idBadge, final int idNouveauBadge) throws OperationImpossible {
		Optional<Badge> badge = chercherBadge(idBadge);
		Optional<Chambre> chambre = chercherChambreBadge(idBadge);
		Optional<Badge> nouveauBadge = Optional.ofNullable(badges.get(idNouveauBadge));
		if (!badge.isPresent()) {
			throw new OperationImpossible("impossible de déclarer perdu un badge inexistant");
		} else if (!chambre.isPresent()) {
			throw new OperationImpossible("impossible de remplacer un badge non attribué");
		} else if (!nouveauBadge.isPresent()) {
			throw new OperationImpossible("impossible d'effectuer le remplacement car le nouveau badge n'existe pas");
		} else if (nouveauBadge.get().estPerdu()) {
			throw new OperationImpossible("impossible d'effectuer le remplacement car le nouveau badge est marqué comme perdu");
		} else if (nouveauBadge.get().getPremiereClef() != null || nouveauBadge.get().getSecondeClef() != null) {
			throw new OperationImpossible("impossible d'effectuer le remplacement car le nouveau badge est déjà utilisé");
		}
		libererChambre(chambre.get().getId());
		enregistrerOccupationChambreClient(chambre.get().getId(), chambre.get().getClient().getId(), idNouveauBadge);
		badge.get().declarerPerte();
	}

	/**
	 * Recherche une chambre associée à un certain badge.
	 *
	 * @param idBadge identifiant du badge
	 * @return Optional qui contient la chambre ou null si celle-ci n'existe pas
	 */
	private Optional<Chambre> chercherChambreBadge(final int idBadge) {
		Optional<Chambre> chambre = null;
		for (Chambre c : chambres.values()) {
			if (c.getBadge().getId() == idBadge) {
				chambre = Optional.ofNullable(c);
			}
		}
		return chambre;
	}
}

