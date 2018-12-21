package com.ca.fastdatamasker.custom;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTituloTest {

    private HashTitulo hasher;

    @Before
    public void setUp() throws Exception {
        hasher = new HashTitulo();
    }

    @Test
    public void testMaskMaintainingStateIsValid() {
        assertTrue("Masked Valid Titulo should also be valid", TituloDeEleitorValidator.isValid(hasher.hashMaintaningState("156106040230")));
    }

    @Test
    public void testMaskWithFixedStateIsValid() {
        assertTrue("Masked Valid Titulo should also be valid", TituloDeEleitorValidator.isValid(hasher.hashWithFixedState("156106040230", "MG")));
    }


    @Test
    public void testMaskChangedTheValue() {
        final String origingalTitulo = "156106040230";
        final String hashedValue = hasher.hashWithFixedState(origingalTitulo, "RR");
        assertNotEquals("Check if code didnt returned the original Titulo", hashedValue,origingalTitulo);
    }

    @Test
    public void testMaskMaintainingDoNotChangedSValue() {
        final String origingalTitulo = "156106040230";
        final String hashedValue = hasher.hashMaintaningState(origingalTitulo);
        assertNotEquals("Check if code didnt returned the original Titulo", hashedValue,origingalTitulo);
    }

    @Test
    public void testIfMaintainingStateDidNotChangeState() {
        final String tituloFromAP = hasher.hashMaintaningState("060858512569");
        final TituloEleitor maskedTituloEleitor = new TituloEleitor(tituloFromAP);
        assertEquals("We choose not to change its original state", maskedTituloEleitor.getUf().getCode(), "25");
    }

    @Test
    public void testIfFixedStateChangedToCorrectOne() {
        final String tituloFromAP = hasher.hashWithFixedState("032888481619", FederalUnity.AL.toString());
        final TituloEleitor maskedTituloEleitor = new TituloEleitor(tituloFromAP);
        assertEquals("We choose to change to a fixed  state", maskedTituloEleitor.getUf().getCode(), FederalUnity.AL.getCode());
    }


    @Test
    public void checkIfMaskFixedMaintainsIntegrity() {
        final String firstHash = hasher.hashWithFixedState("736386181910", FederalUnity.SP.toString());
        final String secondHash = hasher.hashWithFixedState("736386181910", FederalUnity.SP.toString());
        assertEquals("Titulo hashed N times, should always generate the same hashed Titulo", firstHash, secondHash);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testMaskInvalidState() {
        hasher.hashMaintaningState("102617819938");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkMaskInvalidStateChangingState() {
        hasher.hashWithFixedState("102617819938", FederalUnity.MG.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testkMaskInvalidLength() {
        hasher.hashMaintaningState("12160396");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaskToInvalidState() {
        hasher.hashWithFixedState("736386181910", "ZZ");
    }
}