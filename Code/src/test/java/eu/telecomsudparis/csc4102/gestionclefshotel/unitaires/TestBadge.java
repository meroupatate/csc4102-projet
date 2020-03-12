package eu.telecomsudparis.csc4102.gestionclefshotel.unitaires;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.Client;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class TestBadge {

    private Badge badge1;
    private byte[] clef1;
    private byte[] clef2;

    @Before
    public void setUp() throws ChaineDeCaracteresNullOuVide {
        // création d'un badge
        badge1 = new Badge(1);
        // vérification que les clefs du badge créé sont bien nulles
        clef1 = badge1.getPremiereClef();
        clef2 = badge1.getSecondeClef();
        Assert.assertNotNull(badge1);
        Assert.assertNull(clef1);
        Assert.assertNull(clef2);
    }

    @After
    public void tearDown() {
        badge1 = null;
        clef1 = null;
        clef2 = null;
    }

    @Test(expected = ChaineDeCaracteresNullOuVide.class)
    public void constructeurBadgeTest1Jeu1() throws Exception {
        new Badge(0);
    }

    @Test
    public void constructeurBadgeTest2Jeu1() throws Exception {
        Badge badge = new Badge(1);
        Assert.assertNotNull(badge);
        byte[] premiereClef = badge.getPremiereClef();
        Assert.assertNull(premiereClef);
        byte[] secondeClef = badge.getSecondeClef();
        Assert.assertNull(secondeClef);
    }

    @Test
    public void estAttribueTest1Jeu1() {
        badge1.inscrireClefs("[B@7a765367".getBytes(), "[B@76b0bfab".getBytes());
        Assert.assertNotNull(badge1);
        byte[] premiereClef = badge1.getPremiereClef();
        Assert.assertNotNull(premiereClef);
        byte[] secondeClef = badge1.getSecondeClef();
        Assert.assertNotNull(secondeClef);
    }

    @Test
    public void estAttribueTest2Jeu1() {
        byte[] clef1 = "[B@7a765367".getBytes();
        byte[] clef2 = "[B@76b0bfab".getBytes();
        badge1.inscrireClefs(clef1, clef2);
        byte[] premiereClef = badge1.getPremiereClef();
        Assert.assertArrayEquals(clef1, premiereClef);
        byte[] secondeClef = badge1.getSecondeClef();
        Assert.assertArrayEquals(clef2, secondeClef);
    }
}
