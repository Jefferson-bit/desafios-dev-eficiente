package org.example.enums;

public enum PagadorDeJurosEnum {

    CLIENTE(0),
    VENDEDOR(1);


    private int code;

    PagadorDeJurosEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
