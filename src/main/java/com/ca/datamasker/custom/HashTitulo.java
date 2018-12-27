package com.ca.datamasker.custom;

import com.grid_tools.products.datamasker.IMaskFunction;
import com.grid_tools.products.datamasker.randfunctions;

public class HashTitulo implements IMaskFunction {
    @Override
    public Object mask(Object... objects) {
        final String tituloToBeMasked = (String) objects[0];

        String maskedValue = tituloToBeMasked;
        if(tituloToBeMasked != null && !tituloToBeMasked.isEmpty()){
            final String maskingToUF = (String) objects[1];

            if(maskingToUF == null || maskingToUF.isEmpty()){
                maskedValue = hashMaintaningState(tituloToBeMasked);
            }else{
                maskedValue = hashWithFixedState(tituloToBeMasked, maskingToUF);
            }
        }
        return maskedValue;
    }

    protected String hashWithFixedState(final String titulo, final String uf){
        final FederalUnity federalUnity = FederalUnity.valueOf(uf);
        return hashTitulo(titulo, federalUnity);
    }


    protected String hashMaintaningState(final String titulo){
        if(!TituloDeEleitorValidator.isValid(titulo)){
            throw new IllegalArgumentException(titulo+ "is invalid and cannot be hashed");
        }
        final TituloEleitor tituloEleitor = new TituloEleitor(titulo);
        return hashTitulo(titulo, tituloEleitor.getUf());
    }


    private String hashTitulo(final String titulo, final FederalUnity uf){
        if(!TituloDeEleitorValidator.isValid(titulo)){
            throw new IllegalArgumentException(titulo+ "is invalid and cannot be hashed");
        }
        return getHashedTitulo(new TituloEleitor(titulo), uf);
    }

    private String getHashedTitulo(final TituloEleitor tituloOriginal, final FederalUnity uf) {
        final String hashedValue = randfunctions.formatHash(tituloOriginal.getNumber());
        final TituloEleitor hashedTituloEleitor = new TituloEleitor(hashedValue, uf);
        return hashedTituloEleitor.toString();
    }
}
