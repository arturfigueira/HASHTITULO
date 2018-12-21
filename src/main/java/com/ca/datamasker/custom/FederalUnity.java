package com.ca.datamasker.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Also called in Brazil, Unidade Federativa, aka UF. It's the unique code
 * that represents a each state that is part of Brazil.
 * For this project We also have one to represent states from abroad
 */
public enum FederalUnity {
    AC("24"),
    AL("17"),
    AP("25"),
    AM("23"),
    BA("05"),
    CE("07"),
    DF("20"),
    ES("14"),
    GO("10"),
    MA("11"),
    MT("18"),
    MS("19"),
    MG("02"),
    PA("13"),
    PB("12"),
    PR("06"),
    PE("08"),
    PI("15"),
    RJ("03"),
    RN("16"),
    RS("04"),
    RO("23"),
    RR("26"),
    SP("01"),
    SE("21"),
    TO("27"),
    XX("28");

    private final String code;

    FederalUnity(String weight) {
        this.code = weight;
    }

    static FederalUnity getFromCode(final String code){
        FederalUnity convertedUF=null;
        final List<FederalUnity> federalUnities = Arrays.asList(FederalUnity.values());
        final List<FederalUnity> federalUnityStream = federalUnities.stream()
                .filter(uf -> uf.getCode().equals(code))
                .collect(Collectors.toCollection(ArrayList::new));

        if(federalUnityStream.size() == 0){
            throw new IllegalArgumentException("There's no Federeal Unity for the desired code");
        }
        return federalUnityStream.get(0);
    }

    public String getCode() {
        return code;
    }
}
