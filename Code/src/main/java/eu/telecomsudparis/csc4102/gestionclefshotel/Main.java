package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Arrays;
import java.util.Optional;

/**
 * Cette classe peut être utilisée pour écrire des appels de méthodes de la
 * façade de façon complémentaire aux tests de validation, c'est-à-dire par
 * exemple en attendant la programmation des tests de validation.
 * 
 * @author Denis Conan
 */
final class Main {

	/**
	 * Pour éviter l'erreur CheckStyle: "Les classes utilitaires ne doivent pas avoir de constructeur par défaut ou public".
	 */
	private Main() { };

	/**
	 * point d'entrée du scénario.
	 * 
	 * @param args arguments de la ligne de commande.
	 * @throws Exception tous les problèmes.
	 */
	public static void main(final String[] args) throws Exception {
		final int sel1 = 1337;
		GestionClefsHotel gestionClefsHotel = GestionClefsHotel.getInstance();
		gestionClefsHotel.creerChambre(1, "poire Belle-Hélène", sel1);
		gestionClefsHotel.creerClient(1, "Jean", "Michel");
		gestionClefsHotel.creerBadge(1);
		Optional<Chambre> chambre = gestionClefsHotel.chercherChambre(1);
		System.out.println(chambre.get());
		System.out.println(Arrays.toString(chambre.get().getPremiereClef()));
		System.out.println(Arrays.toString(chambre.get().getSecondeClef()));
		Optional<Client> client = gestionClefsHotel.chercherClient(1);
		Optional<Badge> badge = gestionClefsHotel.chercherBadge(1);
		gestionClefsHotel.enregistrerOccupationChambreClient(chambre.get().getId(), client.get().getId(), badge.get().getId());
		System.out.println(chambre.get());
		gestionClefsHotel.reset();
	}

}
