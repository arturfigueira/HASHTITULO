package com.ca.fastdatamasker.custom;

public class TituloDeEleitorValidator {

    private static final int TITULO_LENGHT = 12;

    private TituloDeEleitorValidator(){}

    /**
     * Check whether a {@param titulo} is valid or not
     * @param titulo Number with its UF code and Check Digit
     * @return True if its valid, false otherwise
     */
    public static boolean isValid(final String titulo){
        boolean tituloIsValid = false;
        if(titulo != null && !titulo.isEmpty() && titulo.length() == TITULO_LENGHT){
            final String number = titulo.substring(0, titulo.length() - 4);
            final String codeValue = titulo.substring(titulo.length() - 4, titulo.length() - 2);
            final String checkDigit = titulo.substring(titulo.length() - 2);

            try{
                final FederalUnity uf = FederalUnity.getFromCode(codeValue);
                final TituloEleitor tituloEleitor = new TituloEleitor(number, uf);
                tituloIsValid = tituloEleitor.getCheckDigit().equals(checkDigit);
            }catch (IllegalArgumentException e){
                tituloIsValid = false;
            }

        }
        return tituloIsValid;
    }
}
