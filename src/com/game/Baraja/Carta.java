package com.game.Baraja;

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
        if(bocaAbajo) return "Carta oculta : " + rango.toString() + " de " + palo.toString();
        else {
            if (rango == Rango.joker1) return "Joker1";
            else if  (rango == Rango.joker2) return "Joker2";
            else return rango.toString() + " de " + palo.toString();
        }
    }

    public int getValor() {
        return rango.getValor();
    }

    public Rango getRango() {
        return rango;
    }

    public boolean getBocaAbajo(){
        return bocaAbajo;
    }
}
