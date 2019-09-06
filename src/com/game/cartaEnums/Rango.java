package com.game.cartaEnums;

public enum Rango {
    joker1(0, "Joker"),
    joker2(1 ,"joker"),
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

    Rango(){
        rango = "Joker";
    }

    public int getValor() {
        return valor;
    }

    public String getRango() {
        if (rango == null) return Integer.toString(valor);
        else return rango;
    }

    @Override
    public String toString() {
        return getRango();
    }


}
