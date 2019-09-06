package com.game.cartaEnums;

public enum Rango {
    dos(2),
    tres(3),
    cuatro(4),
    cinco(5),
    seis(6),
    siete(7),
    ocho(8),
    nueve(9),
    diez(10),
    jota(10, "J"),
    reina(10, "Q"),
    rey(10, "K"),
    as(11,"A");

    private int valor;
    private String rango;

    Rango(int valor, String rango) {
        this.valor = valor;
        this.rango = rango;
    }

    Rango(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public String getRango() {
        if (rango == null) return Integer.toString(valor);
        else return rango;
    }
}
