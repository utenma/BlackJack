package com.game;

import com.game.cartaEnums.Palo;
import com.game.cartaEnums.Rango;

public class Carta {
    private Palo palo;
    private Rango rango;
    private boolean bocaAbajo;

    public Carta(Rango rango, Palo palo) {
        this(rango);
        this.palo = palo;
    }

    public Carta(Rango rango){
        this.rango = rango;
        this.bocaAbajo = true;
    }

    public void voltear()
    {
       bocaAbajo = !bocaAbajo;
    }

    @Override
    public String toString() {
        if(bocaAbajo) return "Carta oculta";
        else {
            if (rango == Rango.joker1) return "Joker 1";
            else if (rango == Rango.joker2) return "Joker 2";
            else return rango.toString() + " de " + palo.toString();
        }
    }

    public int getValor() {
        if(bocaAbajo) return 0;
        else return rango.getValor();
    }

    public boolean getBocaAbajo(){
        return bocaAbajo;
    }
}
