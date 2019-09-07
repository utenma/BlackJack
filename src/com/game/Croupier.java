package com.game;

import com.game.Baraja.BarajaInglesa54;
import com.game.Baraja.Carta;

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
        Carta carta = BarajaInglesa54.getBarajaInglesa54().tomarCartaSuperior();
        if (!getMano().isEmpty()) carta.voltear();
        getMano().add(carta);
        System.out.println(this + " tomó una carta");
    }

    @Override
    public String toString() {
        return "Croupier" ;
    }

}
