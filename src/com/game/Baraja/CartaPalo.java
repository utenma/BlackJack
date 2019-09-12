package com.game.Baraja;

import com.util.Consola;

public enum CartaPalo {

    TREBOL(Consola.Color.GREEN + "\u2663" + Consola.Color.RESET), //♣
    CORAZON(Consola.Color.RED + "\u2665" + Consola.Color.RESET),//♥
    ESPADA(Consola.Color.YELLOW + "\u2660" + Consola.Color.RESET), //♠
    DIAMANTE(Consola.Color.PURPLE + "\u2666" + Consola.Color.RESET); //♦

    private String palo;

    CartaPalo(String palo) {
        this.palo = palo;
    }

    public String getPalo() {
        return palo;
    }

    @Override
    public String toString() {
        return palo;
    }
}
