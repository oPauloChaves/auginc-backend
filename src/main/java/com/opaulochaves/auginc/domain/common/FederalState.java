package com.opaulochaves.auginc.domain.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FederalState {

    AC("Acre"), AL("Alagoas"), AP("Amapá"), AM("Amazonas"),
    BA("Bahia"), CE("Ceará"), DF("Distrito Federal"), ES("Espírito Santo"),
    GO("Goiás"), MA("Maranhão"), MT("Mato Grosso"), MS("Mato Grosso do Sul"),
    MG("Minas Gerais"), PA("Pará"), PB("Paraíba"), PR("Paraná"),
    PE("Pernambuco"), PI("Piauí"), RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"), RS("Rio Grande do Sul"), RO("Rondônia"),
    RR("Roraima"), SC("Santa Catarina"), SP("São Paulo"), SE("Sergipe"),
    TO("Tocantins");

    private String state;

    FederalState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    @JsonValue
    public String getName() {
        return this.toString();
    }
}
