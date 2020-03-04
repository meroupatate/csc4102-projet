package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Optional;

/**
 * Cette classe peut être utilisée pour écrire des appels de méthodes de la
 * façade de façon complémentaire aux tests de validation, c'est-à-dire par
 * exemple en attendant la programmation des tests de validation.
 * 
 * @author Denis Conan
 */
public class Main {
	/**
	 * point d'entrée du scénario.
	 * 
	 * @param args arguments de la ligne de commande.
	 * @throws Exception tous les problèmes.
	 */
	public static void main(String[] args) throws Exception {
		GestionClefsHotel gestionClefsHotel = new GestionClefsHotel();
		gestionClefsHotel.creerChambre(1, "poire Belle-Hélène", 1337);
		Optional<Chambre> chambre = gestionClefsHotel.chercherChambre(1);
		System.out.println(chambre.get().getPremiereClef());
		System.out.println(chambre.get().getSecondeClef());
	}

}
