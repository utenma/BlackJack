package com.game;

import com.game.cartaEnums.Palo;
import com.game.cartaEnums.Rango;

public class BlackJack {

    public static void main(String[] args) {

        for(Palo i : Palo.values())
        {
            System.out.println(i.name() + " :: "+ i.getPalo());
        }

        System.out.println();

        for(Rango i : Rango.values())
        {
            System.out.println(i.name() + " :: "+ i.getValor());
        }

        Baraja baraja = new Baraja();

    }



}
