package com.game.Baraja;

import java.util.Collections;

public class BarajaInglesa54 {

    private CardArrayList<Carta> cartas;
    private static BarajaInglesa54 barajaInglesa54;

    private BarajaInglesa54() {
        inicializar();
    }

    public static BarajaInglesa54 getBarajaInglesa54() {
        if (barajaInglesa54 == null) barajaInglesa54 = new BarajaInglesa54();
        return barajaInglesa54;
    }

    public void inicializar() {
        cartas = new CardArrayList<Carta>();
        for (CartaRango rango : CartaRango.values()) {
            if (rango.getValor() == 1) continue;
            for (CartaPalo j : CartaPalo.values()) {
                cartas.add(new Carta(rango, j));
            }
        }
        cartas.add(new Carta(CartaRango.JOKER1));
        cartas.add(new Carta(CartaRango.JOKER_2));
        System.out.println("Baraja Lista");
    }

    public void mostrar() {
        int index = 1;
        for (Carta carta : cartas) {
            System.out.println("Carta " + index++ + " en baraja " + carta);
        }
    }

    /**
     * Este método barajera la baraja de manera aleatoria
     */
    public void barajar() {
        Collections.shuffle(cartas);
        System.out.println("La baraja ha sido barajada");
    }

    public Carta tomarCartaSuperior() {
        return tomarCartaEnIndice(0);
    }

    public Carta[] tomarCartasSuperiores(int cantidad) {
        return tomarCartasEnIndice(0, cantidad);
    }

    public Carta tomarCartaInferior() {
        return tomarCartaEnIndice(cartas.size() - 1);
    }

    public Carta tomarCartaEnIndice(int posicion) {
        if (!barajaInglesa54.cartas.isEmpty()) {
            Carta carta = barajaInglesa54.cartas.get(posicion);
            barajaInglesa54.cartas.remove(posicion);
            return carta;
        } else return null;
    }

    public Carta[] tomarCartasEnIndice(int posicion, int cantidad) {
        Carta[] cartas = new Carta[cantidad];
        for (int i = 0; i < cantidad; i++) {
            if (!barajaInglesa54.cartas.isEmpty()) {
                cartas[i] = barajaInglesa54.cartas.get(posicion);
                barajaInglesa54.cartas.remove(posicion);
            } else return null;
        }
        return cartas;
    }

    public int getCantidadDeCartas() {
        return cartas.size();
    }
}
