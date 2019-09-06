package com.game;

import com.game.cartaEnums.Palo;
import com.game.cartaEnums.Rango;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    private ArrayList<Carta> cartas;

    public Baraja() {

        for(Palo i : Palo.values())
        {
            cartas = new ArrayList<Carta>();
            for(Rango j : Rango.values())
            {
                Carta carta = new Carta(i,j);
                cartas.add(carta);
                System.out.println(carta);
            }
        }



    }

    public void barajar(){
        Collections.shuffle(cartas);
    }

    public void tomarCartaSuperior(){

    }

    public void tomarCartaInferior(){

    }

    public void tomarCartaEnPosicion(int posicion){

    }

    public void tomarCartaAleatoria(){

    }
}
