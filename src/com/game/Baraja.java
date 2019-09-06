package com.game;

import com.game.cartaEnums.Palo;
import com.game.cartaEnums.Rango;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Baraja {

    private ArrayList<Carta> cartas;

    private static Baraja baraja;

    private Baraja() {
        cartas = new ArrayList<Carta>();
        for (Rango i : Rango.values()) {
            if ((i == Rango.joker1) || (i == Rango.joker2)) continue;
            for (Palo j : Palo.values()) {
                cartas.add(new Carta(i, j));
            }
        }
            cartas.add(new Carta(Rango.joker1));
            cartas.add(new Carta(Rango.joker2));
    }

    public static Baraja getBaraja() {
        if (baraja == null) baraja = new Baraja();
        return baraja;
    }

    public void mostrar() {
        int index = 1;
        for (Carta carta : cartas) {
            System.out.println("Carta " + index++ + " en baraja " + carta);
        }
    }

    public void barajar() {
        Collections.shuffle(cartas);
        System.out.println("La baraja ha sido barajada");
    }

    public Carta tomarCartaSuperior() {
        return tomarCartaEnIndice(cartas.size()-1);
    }

    public Carta tomarCartaInferior() {
        return tomarCartaEnIndice(0);
    }


    public Carta tomarCartaAleatoria() {
        Random random = new Random();
        return tomarCartaEnIndice(random.nextInt());
    }

    public Carta tomarCartaEnIndice(int posicion) {
        if (!baraja.cartas.isEmpty()){
            Carta carta = baraja.cartas.get(posicion);
            baraja.cartas.remove(posicion);
            return carta;
        }
        else return null;
    }
}
