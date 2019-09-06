package com.game;

import com.game.cartaEnums.Palo;
import com.game.cartaEnums.Rango;

import java.util.ArrayList;
import java.util.Arrays;

public class BlackJack {

    public static void main(String[] args) {

        System.out.println("Enum de palo");
        for(Palo i : Palo.values()) System.out.println(i.name() + " : "+ i.getPalo());

        System.out.println();
        System.out.println("Enum de rango");
        for(Rango i : Rango.values()) System.out.println(i.name() + " : "+ i.getValor());

        Baraja baraja = Baraja.getBaraja();
        System.out.println();
        Croupier.getCroupier();
        new Jugador("Eduardo");
        new Jugador("Daniel");

        System.out.println();
        System.out.println("Lista de Jugadores");
        for(Jugador jugador: Jugador.jugadores) System.out.println(jugador);
    }



}
