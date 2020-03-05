package eu.telecomsudparis.csc4102.gestionclefshotel;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;
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
	 * construit la façade.
	 */

	private Map<Integer, Client> clients;
	private Map<Integer, Badge> badges;
	private Map<Integer, Chambre> chambres;

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

	public void creerChambre(final int id, final String graine, final int sel) throws OperationImpossible {
		if (id == 0) {
			throw new ChaineDeCaracteresNullOuVide("identifiant nul non autorisé");
		}
		else if (graine == null) {
			throw new ChaineDeCaracteresNullOuVide("graine null non autorisée");
		}
		else if (chercherChambre(id) != null) {
			throw new OperationImpossible("impossible de créer la chambre avec un identifiant déjà utilisé");
		}
		chambres.put(id, new Chambre(id, graine, sel));
	}

	public void creerClient(final int id, final String nom, final String prenom) throws OperationImpossible {
		if (id == 0) {
			throw new ChaineDeCaracteresNullOuVide("identifiant nul non autorisé");
		}
		else if (nom == null || prenom == null) {
			throw new ChaineDeCaracteresNullOuVide("nom ou prénom null non autorisé");
		}
		else if (chercherClient(id) != null) {
			throw new OperationImpossible("impossible de créer un client avec un identifiant déjà utilisé");
		}
		clients.put(id, new Client(id, nom, prenom));
	}

	public void creerBadge(final int id) throws OperationImpossible {
		if (id == 0) {
			throw new ChaineDeCaracteresNullOuVide("identifiant nul non autorisé");
		}
		else if (chercherBadge(id) != null) {
			throw new OperationImpossible("impossible de créer un badge avec un identifiant déjà utilisé");
		}
		badges.put(id, new Badge(id));
	}

	Optional<Chambre> chercherChambre(final int id) {
		return Optional.ofNullable(chambres.get(id));
	}

	Optional<Client> chercherClient(final int id) {
		return Optional.ofNullable(clients.get(id));
	}

	Optional<Badge> chercherBadge(final int id) {
		return Optional.ofNullable(badges.get(id));
	}

	public void enregistrerOccupationChambreClient(final int id_chambre, final int id_client, final int id_badge) throws OperationImpossible {
		Optional<Chambre> chambre = chercherChambre(id_chambre);
		Optional<Client> client = chercherClient(id_client);
		Optional<Badge> badge = chercherBadge(id_badge);
		if (!client.isPresent()) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre par un client inexistant");
		}
		else if (client.get().getNom() == null || client.get().getPrenom() == null) {
			throw new ChaineDeCaracteresNullOuVide("nom ou prénom du client null non autorisé");
		}
		else if (!chambre.isPresent()) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre inexistante");
		}
		else if (chambre.get().getClient() == null) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre déjà occupée");
		}
		else if (!badge.isPresent()) {
			throw new OperationImpossible("impossible d'enregistrer l'occupation d'une chambre avec un badge inexistant");
		}
		chambre.get().associerClientBadge(client.get(), badge.get());
		byte[] nouvelleClef = Util.genererUneNouvelleClef(chambre.get().getGraine(), String.format("%010d%n", chambre.get().getSel()));
		badge.get().inscrireClefs(chambre.get().getSecondeClef(), nouvelleClef);
	}

	public void libererChambre(final int id_chambre) throws OperationImpossible {
		Optional<Chambre> chambre = chercherChambre(id_chambre);
		Optional<Badge> badge = Optional.ofNullable(chambre.get().getBadge());
		Optional<Client> client = Optional.ofNullable(chambre.get().getClient());
		if (!chambre.isPresent()) {
			throw new OperationImpossible("impossible de libérer une chambre inexistante");
		}
		else if (!badge.isPresent() || !client.isPresent()) {
			throw new OperationImpossible("impossible de libérer une chambre inoccupée");
		}
		chambre.get().liberer();
		badge.get().effacerClefs();
	}


}

