package com.game;

import java.util.ArrayList;

public class Croupier extends Jugador {

    private static Croupier croupier;

    private Croupier() {
        super();
        System.out.println("Croupier Listo ");
    }

    public static Croupier getCroupier() {
        if (croupier == null) croupier = new Croupier();
        return croupier;
    }

    @Override
    public void pedirCarta(){
        Carta carta = Baraja.getBaraja().tomarCartaSuperior();
        if (!mano.isEmpty()) carta.voltear();
        mano.add(carta);
    }

    @Override
    public String toString() {
        return "Croupier" ;
    }

}
