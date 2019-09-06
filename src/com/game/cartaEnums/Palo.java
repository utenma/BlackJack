package com.game.cartaEnums;

public enum Palo {

    trebol("\u2663"), //♣
    corazon("\u2665"),//♥
    espada("\u2660"), //♠
    diamante("\u2666"); //♦

    private String palo;

    Palo(String palo) {
        this.palo = palo;
    }

    public String getPalo() {
        return palo;
    }
}
