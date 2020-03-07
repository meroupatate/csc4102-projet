package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestEnregistrerOccupationChambreClient {

    private GestionClefsHotel systeme;

    @Before
    public void setUp() throws OperationImpossible {
        systeme = new GestionClefsHotel();
        systeme.creerChambre(1, "graine1", 0);
        systeme.creerClient(1, "jean", "michel");
        systeme.creerClient(2, "jeanne", "micheline");
        systeme.creerBadge(1);
        systeme.creerBadge(2);
    }

    @After
    public void tearDown() {
        systeme = null;
    }

    @Test(expected = OperationImpossible.class)
    public void enregistrerOccupationChambreClientTest1Jeu1() throws Exception {
        systeme.enregistrerOccupationChambreClient(0, 1, 1);
    }

    @Test(expected = OperationImpossible.class)
    public void enregistrerOccupationChambreClientTest2Jeu1() throws Exception {
        systeme.enregistrerOccupationChambreClient(1, 0, 1);
    }

    @Test(expected = OperationImpossible.class)
    public void enregistrerOccupationChambreClientTest3Jeu1() throws Exception {
        systeme.enregistrerOccupationChambreClient(1, 1, 0);
    }

    @Test(expected = OperationImpossible.class)
    public void enregistrerOccupationChambreClientTest4Jeu1() throws Exception {
        systeme.enregistrerOccupationChambreClient(1, 1, 1);
        systeme.enregistrerOccupationChambreClient(1, 2, 2);
    }
}
