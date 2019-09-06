package com.game;

import com.game.cartaEnums.Palo;
import com.game.cartaEnums.Rango;

public class Carta {
    private Palo palo;
    private Rango rango;
    private boolean lado;

    public Carta(Rango rango, Palo palo) {
        this.rango = rango;
        this.palo = palo;
        this.lado = false;
    }

    public Carta(Rango rango){
        this.rango = rango;
        this.lado = false;
    }

    public void voltear()
    {
       lado = !lado;
    }

    @Override
    public String toString() {
        if(rango == Rango.joker1) return "Joker 1";
        else if(rango == Rango.joker2) return "Joker 2";
        else return rango.toString() + " de " +  palo.toString();

    }
}
