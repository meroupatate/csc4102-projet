package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCreerChambre {

    private GestionClefsHotel systeme;

    @Before
    public void setUp() {
        systeme = new GestionClefsHotel();
    }

    @After
    public void tearDown() {
        systeme = null;
    }

    @Test(expected = ChaineDeCaracteresNullOuVide.class)
    public void creerChambreTest1Jeu1() throws Exception {
        systeme.creerChambre(0, "graine1", 0);
    }

    @Test(expected = ChaineDeCaracteresNullOuVide.class)
    public void creerChambreTest2Jeu1() throws Exception {
        systeme.creerChambre(1, null, 0);
    }

    @Test(expected = ChaineDeCaracteresNullOuVide.class)
    public void creerChambreTest2Jeu2() throws Exception {
        systeme.creerChambre(1, "", 0);
    }

    @Test(expected = OperationImpossible.class)
    public void creerChambreTest4Puis3() throws Exception {
        try {
            systeme.creerChambre(1, "graine2", 0);
        } catch (OperationImpossible e) {
            Assert.fail();
        }
        systeme.creerChambre(1, "graine2", 0);
    }
}
