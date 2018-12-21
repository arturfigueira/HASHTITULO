package com.ca.fastdatamasker.custom;

import org.junit.Test;

import static org.junit.Assert.*;

public class TituloDeEleitorValidatorTest {

    @Test
    public void checkValidTitulo() {
        assertTrue("This Titulo is VALID", TituloDeEleitorValidator.isValid("156106040230"));
    }

    @Test
    public void checkValidTituloFromAbroad() {
        assertTrue("This Titulo is VALID", TituloDeEleitorValidator.isValid("156327812836"));
    }

    @Test
    public void checkInvalidLengthTitulo() {
        assertFalse("This Titulo has invalid lenght", TituloDeEleitorValidator.isValid("16800388"));
    }


    @Test
    public void checkInvalidUFTitulo() {
        assertFalse("This Titulo has an unexistent UF code", TituloDeEleitorValidator.isValid("152687813352"));
    }

    @Test
    public void checkInvalidCheckDigitTitulo() {
        assertFalse("This Titulo has an invalid CheckDigit", TituloDeEleitorValidator.isValid("115478322700"));
    }

    @Test
    public void checkNullTitulo() {
        assertFalse("Null is considered invalid", TituloDeEleitorValidator.isValid(null));
    }

    @Test
    public void checkEmptyTitulo() {
        assertFalse("A String with only spaces is considered invalid", TituloDeEleitorValidator.isValid("      "));
    }
}