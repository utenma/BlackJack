package com.game.Baraja;

import com.util.Consola;

public enum CartaRango {
    JOKER1(1 ,Consola.Color.PURPLE + "JOKER1" + Consola.Color.RESET),
    JOKER_2(1 ,Consola.Color.PURPLE + "JOKER2" + Consola.Color.RESET),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    OCHO(8),
    NUEVE(9),
    DIEZ(10),
    JOTA(10, Consola.Color.GREEN + "J" + Consola.Color.RESET),
    REINA(10, Consola.Color.GREEN + "Q" + Consola.Color.RESET),
    REY(10, Consola.Color.GREEN + "K" + Consola.Color.RESET),
    AS(11,Consola.Color.PURPLE + "A" + Consola.Color.RESET);

    private int valor;
    private String rango;

    CartaRango(int valor, String rango) {
        this.valor = valor;
        this.rango = rango;
    }

    CartaRango(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        if (rango == null) return Consola.Color.YELLOW + Integer.toString(valor) + Consola.Color.RESET;
        else return rango;
    }


}
