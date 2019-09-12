package com.game.Baraja;

public class Carta {
    private CartaPalo palo;
    private CartaRango rango;
    private boolean bocaAbajo;

    public Carta(CartaRango rango, CartaPalo palo) {
        this(rango);
        this.palo = palo;
    }

    public Carta(CartaRango rango){
        this.rango = rango;
        this.bocaAbajo = true;
    }

    public void voltear()
    {
       bocaAbajo = !bocaAbajo;
    }

    @Override
    public String toString() {
        String carta = "";
        if(bocaAbajo) carta += "Carta oculta : ";

        if (rango == CartaRango.JOKER1 || rango == CartaRango.JOKER_2) carta += rango;
        else carta += rango + " de " + palo;
        return carta;
    }

    public void setValorRango(int valor) {
        this.rango.setValor(valor);
    }

    public int getValor() {
        return rango.getValor();
    }

    public CartaRango getRango() {
        return rango;
    }

    public boolean getBocaAbajo(){
        return bocaAbajo;
    }
}
