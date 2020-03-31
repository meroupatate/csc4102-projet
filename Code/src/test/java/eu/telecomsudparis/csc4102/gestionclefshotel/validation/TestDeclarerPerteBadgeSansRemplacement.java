package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDeclarerPerteBadgeSansRemplacement {

    private GestionClefsHotel systeme;

    @Before
    public void setUp() throws OperationImpossible {
        systeme = new GestionClefsHotel();
        systeme.creerChambre(1, "graine", 0);
        systeme.creerClient(1, "jean", "michel");
        systeme.creerBadge(1);
        systeme.enregistrerOccupationChambreClient(1, 1, 1);
    }

    @After
    public void tearDown() {
        systeme = null;
    }

    @Test(expected = OperationImpossible.class)
    public void declarerPerteBadgeSansRemplacementTest1Jeu1() throws Exception {
        systeme.declarerPerteBadgeSansRemplacement(0);
    }

    @Test(expected = OperationImpossible.class)
    public void declarerPerteBadgeSansRemplacementTest2Jeu1() throws Exception {
        systeme.declarerPerteBadgeSansRemplacement(2);
    }

    @Test
    public void declarerPerteBadgeSansRemplacementTest3Jeu1() throws OperationImpossible {
        Assert.assertNotNull(systeme.chercherChambre(1).get().getBadge());
        Assert.assertNotNull(systeme.chercherChambre(1).get().getClient());
        Assert.assertNotNull(systeme.chercherBadge(1).get().getPremiereClef());
        Assert.assertNotNull(systeme.chercherBadge(1).get().getSecondeClef());
        Assert.assertFalse(systeme.chercherBadge(1).get().estPerdu());
        systeme.declarerPerteBadgeSansRemplacement(1);
        Assert.assertNull(systeme.chercherChambre(1).get().getBadge());
        Assert.assertNull(systeme.chercherChambre(1).get().getClient());
        Assert.assertNull(systeme.chercherBadge(1).get().getPremiereClef());
        Assert.assertNull(systeme.chercherBadge(1).get().getSecondeClef());
        Assert.assertTrue(systeme.chercherBadge(1).get().estPerdu());
    }
}
