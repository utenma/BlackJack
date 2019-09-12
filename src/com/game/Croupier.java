package com.game;

import com.game.Baraja.BarajaInglesa54;
import com.game.Baraja.Carta;
import com.util.Consola;
//Todo set nombre to cropupier

public class Croupier extends Jugador {

    private static Croupier croupier;

    private Croupier(String nombre) {
        super();
        this.nombre = nombre;
        System.out.println("Croupier " + Consola.Color.GREEN + "Listo" + Consola.Color.RESET);
    }

    public static Croupier getCroupier() {
        if (croupier == null) croupier = new Croupier("Croupier");
        return croupier;
    }

    @Override
    public void pedirCarta(){
        Carta carta = BarajaInglesa54.getBarajaInglesa54().tomarCartaSuperior();
        if (!getMano().isEmpty()) carta.voltear();
        getMano().add(carta);
        System.out.println(this + " tomó una carta | " + carta + " |");
    }

    @Override
    public void pedirCartas(int cantidad){
        Carta[] cartas = BarajaInglesa54.getBarajaInglesa54().tomarCartasSuperiores(cantidad);
        for(Carta carta : cartas){
            if (!getMano().isEmpty()) carta.voltear();
            getMano().add(carta);
        }
        System.out.print(this + " pidió " + cantidad + " cartas : | ");
        for(Carta carta : cartas) System.out.print( carta + " | ");
        System.out.println();
    }
}
