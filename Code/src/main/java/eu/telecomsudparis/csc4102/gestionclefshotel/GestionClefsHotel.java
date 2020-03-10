package eu.telecomsudparis.csc4102.gestionclefshotel;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Cette classe définit la façade du système de gestion des clefs de l'hôtel.
 * 
 * @author Denis Conan
 */
public class GestionClefsHotel {

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
	 * Constructeur de la façade du système de gestion des clefs de l'hotel.
	 */
	public GestionClefsHotel() {
		clients = new HashMap<>();
		badges = new HashMap<>();
		chambres = new HashMap<>();
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
	 * @param id
	 * @param graine
	 * @param sel
	 * @throws OperationImpossible si la chambre n'existe pas.
	 */
	public void creerChambre(final int id, final String graine, final int sel) throws OperationImpossible {
		if (id == 0) {
			throw new ChaineDeCaracteresNullOuVide("identifiant nul non autorisé");
		} else if (graine == null || graine == "") {
			throw new ChaineDeCaracteresNullOuVide("graine null ou vide non autorisée");
		} else if (chercherChambre(id).isPresent()) {
			throw new OperationImpossible("impossible de créer la chambre avec un identifiant déjà utilisé");
		}
		chambres.put(id, new Chambre(id, graine, sel));
	}

	/**
	 * Crée un client dans le système avec un identifiant, un nom et un prénom.
	 * @param id
	 * @param nom
	 * @param prenom
	 * @throws OperationImpossible
	 */
	public void creerClient(final int id, final String nom, final String prenom) throws OperationImpossible {
		if (id == 0) {
			throw new ChaineDeCaracteresNullOuVide("identifiant nul non autorisé");
		} else if (nom == null || prenom == null || nom == "" || prenom == "") {
			throw new ChaineDeCaracteresNullOuVide("nom ou prénom null ou vide non autorisé");
		} else if (chercherClient(id).isPresent()) {
			throw new OperationImpossible("impossible de créer un client avec un identifiant déjà utilisé");
		}
		clients.put(id, new Client(id, nom, prenom));
	}

	/**
	 * Crée un badge dans le système avec un identifiant.
	 * @param id
	 * @throws OperationImpossible
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
	 * @param id
	 * @return Optional qui contient la chambre.
	 * @throws OperationImpossible
	 */
	public Optional<Chambre> chercherChambre(final int id) throws OperationImpossible {
		if (id == 0) {
			throw new OperationImpossible("impossible de rechercher une chambre avec un identifiant nul");
		}
		return Optional.ofNullable(chambres.get(id));
	}

	/**
	 * Recherche un client dans le système à partir d'un identifiant.
	 * @param id
	 * @return Optional qui contient le client.
	 * @throws OperationImpossible
	 */
	public Optional<Client> chercherClient(final int id) throws OperationImpossible {
		if (id == 0) {
			throw new OperationImpossible("impossible de rechercher un client avec un identifiant nul");
		}
		return Optional.ofNullable(clients.get(id));
	}

	/**
	 * Recherche un badge dans le système à partir d'un identifiant.
	 * @param id
	 * @return Optional qui contient le badge.
	 * @throws OperationImpossible
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
	 * @param idChambre
	 * @param idClient
	 * @param idBadge
	 * @throws OperationImpossible
	 */
	public void enregistrerOccupationChambreClient(final int idChambre, final int idClient, final int idBadge) throws OperationImpossible {
		Optional<Chambre> chambre = chercherChambre(idChambre);
		Optional<Client> client = chercherClient(idClient);
		Optional<Badge> badge = chercherBadge(idBadge);
		if (!client.isPresent()) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre par un client inexistant");
		} else if (client.get().getNom() == null || client.get().getPrenom() == null || client.get().getPrenom() == "" || client.get().getNom() == "") {
			throw new ChaineDeCaracteresNullOuVide("nom ou prénom du client null ou vide non autorisé");
		} else if (!chambre.isPresent()) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre inexistante");
		} else if (chambre.get().getClient() != null || chambre.get().getBadge() != null) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre déjà occupée");
		} else if (!badge.isPresent()) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre avec un badge inexistant");
		}
		chambre.get().associerClientBadge(client.get(), badge.get());
		byte[] nouvelleClef = Util.genererUneNouvelleClef(chambre.get().getGraine(), String.format("%010d%n", chambre.get().getSel()));
		badge.get().inscrireClefs(chambre.get().getSecondeClef(), nouvelleClef);
	}

	/**
	 * Libère une chambre du système.
	 * @param idChambre
	 * @throws OperationImpossible
	 */
	public void libererChambre(final int idChambre) throws OperationImpossible {
		Optional<Chambre> chambre = chercherChambre(idChambre);
		Optional<Badge> badge = Optional.ofNullable(chambre.get().getBadge());
		Optional<Client> client = Optional.ofNullable(chambre.get().getClient());
		if (!chambre.isPresent()) {
			throw new OperationImpossible("impossible de libérer une chambre inexistante");
		} else if (!badge.isPresent() || !client.isPresent()) {
			throw new OperationImpossible("impossible de libérer une chambre inoccupée");
		}
		chambre.get().liberer();
		badge.get().effacerClefs();
	}
}

