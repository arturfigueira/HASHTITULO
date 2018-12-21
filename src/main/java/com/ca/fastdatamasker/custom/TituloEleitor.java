package com.ca.fastdatamasker.custom;

/**
 * This class represents a Brazilian Vote Register (aka Titulo de Eleitor)
 */
public class TituloEleitor {
    private final String number;
    private final String checkDigit;
    private final FederalUnity uf;

    private static final int NUMBER_WEIGHT = 2;
    private static final int CODE_WEIGHT = 7;

    /**
     * Create a Titulo de Eleitor based on its number, UF and checkDigit
     * @param number Without CheckDigit
     * @param checkDigit CheckDigit
     * @param uf Unidade Federativa
     */
    public TituloEleitor(final String number, final String checkDigit, final FederalUnity uf) {
        this.number = number;
        this.checkDigit = checkDigit;
        this.uf = uf;
    }

    /**
     * Create a Titulo de Eleitor based on its number, UF. Checkdigit will be calculated automatically
     * @param number Without CheckDigit
     * @param uf Unidade Federativa
     */
    public TituloEleitor(final String number, final FederalUnity uf) {
        this.number = number;
        this.checkDigit = calculateDigit(number, uf);
        this.uf = uf;
    }

    public String getNumber() {
        return number;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public FederalUnity getUf() {
        return uf;
    }

    private String calculateDigit(final String number, final FederalUnity uf){
        final int firstDigit = calculateDigit(number,NUMBER_WEIGHT);
        final int secondDigit = calculateDigit(uf.getCode()+firstDigit,CODE_WEIGHT);
        return ""+firstDigit+secondDigit;
    }

    private int calculateDigit(final String number, final int initialWeight){
        int sumValue = 0;
        for(int i=0; i<number.length(); i++){
            final int numericValue = Character.getNumericValue(number.charAt(i));
            sumValue += numericValue * (initialWeight+i);
        }
        return mod11(sumValue);
    }

    private static int mod11(final int value){
        int mod11 = value % 11;
        return (mod11 > 9) ? 0 : mod11;
    }

    @Override
    public String toString() {
        return String.format("%s%d%s", number,uf.getCode(),checkDigit);
    }
}
