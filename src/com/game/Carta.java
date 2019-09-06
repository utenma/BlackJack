package com.game;

import com.game.cartaEnums.Palo;
import com.game.cartaEnums.Rango;

public class Carta {
    private Palo palo;
    private Rango rango;
    private boolean lado;

    public Carta(Palo palo, Rango rango) {
        this.palo = palo;
        this.rango = rango;
        this.lado = false;
    }

    public void voltear()
    {
       lado = !lado;
    }

    @Override
    public String toString() {
        return "Carta : " + palo.toString() + " " + rango.toString();
    }
}
