package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestLibererChambre {

    private GestionClefsHotel systeme;

    @Before
    public void setUp() throws OperationImpossible {
        systeme = GestionClefsHotel.getInstance();
        systeme.creerChambre(1, "graine1", 0);
        systeme.creerChambre(2, "graine2", 0);
        systeme.creerClient(1, "jean", "michel");
        systeme.creerBadge(1);
        systeme.enregistrerOccupationChambreClient(1, 1, 1);
    }

    @After
    public void tearDown() {
        systeme.reset();
    }

    @Test(expected = OperationImpossible.class)
    public void libererChambreTest1Jeu1() throws Exception {
        systeme.libererChambre(0);
    }

    @Test(expected = OperationImpossible.class)
    public void libererChambreTest2Jeu1() throws Exception {
        systeme.libererChambre(3);
    }

    @Test(expected = OperationImpossible.class)
    public void libererChambreTest3Jeu1() throws Exception {
        systeme.libererChambre(2);
    }

    @Test
    public void libererChambreTest4Jeu1() throws Exception {
        Assert.assertNotNull(systeme.chercherChambre(1).get().getBadge());
        Assert.assertNotNull(systeme.chercherChambre(1).get().getClient());
        systeme.libererChambre(1);
        Assert.assertNull(systeme.chercherChambre(1).get().getBadge());
        Assert.assertNull(systeme.chercherChambre(1).get().getClient());
    }
}
