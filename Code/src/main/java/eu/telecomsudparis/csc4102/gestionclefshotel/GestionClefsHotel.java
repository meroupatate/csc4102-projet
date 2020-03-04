package eu.telecomsudparis.csc4102.gestionclefshotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;

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
		return chambres != null;
	}

	public void creerChambre(final int id, final String graine, final int sel) throws ProblemeDansGenerationClef {
		chambres.put(id, new Chambre(id, graine, sel));
		assert invariant();
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

	public void enregistrerOccupationChambreClient(final int id_chambre, final int id_client, final int id_badge) throws ProblemeDansGenerationClef {
		Optional<Chambre> chambre = chercherChambre(id_chambre);
		Optional<Client> client = chercherClient(id_client);
		Optional<Badge> badge = chercherBadge(id_badge);
		chambre.get().associerClient(client.get());
		chambre.get().associerBadge(badge.get());
		byte[] nouvelleClef = Util.genererUneNouvelleClef(chambre.get().getGraine(), String.format("%010d%n", chambre.get().getSel()));
		badge.get().inscrireClefs(chambre.get().getSecondeClef(), nouvelleClef);
	}

	public void libererChambre(final int id_chambre) {
		Optional<Chambre> chambre = chercherChambre(id_chambre);
		Optional<Badge> badge = Optional.ofNullable(chambre.get().getBadge());
		chambre.get().liberer();
		badge.get().effacerClefs();
	}


}

