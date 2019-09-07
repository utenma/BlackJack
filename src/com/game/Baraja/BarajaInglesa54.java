package com.game.Baraja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BarajaInglesa54 {

    private ArrayList<Carta> cartas;
    private static BarajaInglesa54 barajaInglesa54;

    private BarajaInglesa54() {
        cartas = new ArrayList<Carta>();
        for (Rango rango : Rango.values()) {
            if (rango.getValor() == 1 ) continue;
            for (Palo j : Palo.values()) {
                cartas.add(new Carta(rango, j));
            }
        }
            cartas.add(new Carta(Rango.joker1));
            cartas.add(new Carta(Rango.joker2));
        System.out.println("Baraja Lista");
    }

    public static BarajaInglesa54 getBarajaInglesa54() {
        if (barajaInglesa54 == null) barajaInglesa54 = new BarajaInglesa54();
        return barajaInglesa54;
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
        if (!barajaInglesa54.cartas.isEmpty()){
            Carta carta = barajaInglesa54.cartas.get(posicion);
            barajaInglesa54.cartas.remove(posicion);
            return carta;
        }
        else return null;
    }

    public int getCantidadDeCartas() {
        return cartas.size();
    }
}
