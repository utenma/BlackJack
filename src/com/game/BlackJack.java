package com.game;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class BlackJack {

    public static void main(String[] args) {
 /*       System.out.println("Enum de palo");
        for(Palo i : Palo.values()) System.out.println(i.name() + " : "+ i.getPalo());

        System.out.println();
        System.out.println("Enum de rango");
        for(Rango i : Rango.values()) System.out.println(i.name() + " : "+ i.getValor());*/

        BarajaInglesa54 baraja = BarajaInglesa54.getBarajaInglesa54();
        baraja.barajar();
        System.out.println();
        Croupier croupier = Croupier.getCroupier();
        Jugador j1 = new Jugador("Eduardo");
        Jugador j2 = new Jugador("Daniel");
        Jugador j3 = new Jugador("Victor");

        for (int i = 0; i < 2; i++) {
            System.out.println();
            System.out.println("La baraja tiene " + baraja.getCantidadDeCartas() + " cartas");
            Jugador.jugadores.forEach(jugador -> jugador.pedirCarta());
        }

        System.out.println();
        for (Jugador jugador : Jugador.jugadores) verCartasDelJugador(jugador);
    }

    public static void verCartasDelJugador(Jugador jugador){
        int [] puntaje = jugador.getPuntajeMano();
        if (puntaje[1]>0){
            //System.out.println(jugador + " tiene en su mano " + puntaje[0] + " + x puntos, donde " + puntaje[1]*1 + " < x < " + puntaje[1]*11);
            System.out.println(jugador + " tiene en su mano de " + (puntaje[0] + puntaje[1]*1) + " hasta " + (puntaje[0] + puntaje[1]*11) + " puntos ");
        }
        else{
            System.out.println(jugador + " tiene en su mano " + puntaje[0] +" puntos");
        }

        jugador.getMano().forEach(carta -> System.out.println(carta));
    }



}
