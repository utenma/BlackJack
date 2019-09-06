package com.game.cartaEnums;

public enum Rango {
    dos(2, "2"),
    tres(3, "3"),
    cuatro(4, "4"),
    cinco(5, "5"),
    seis(6, "6"),
    siete(7,"7"),
    ocho(8, "8"),
    nueve(9, "9"),
    diez(10, "10"),
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

    public int getValor() {
        return valor;
    }

    public String getRango() {
        if (rango == null) return Integer.toString(valor);
        else return rango;
    }
}
