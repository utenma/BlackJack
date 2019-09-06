package com.game;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class Jugador {

    protected ArrayList<Carta> mano;
    protected String nombre;
    public static ArrayList<Jugador> jugadores = new ArrayList<>();

    public Jugador(String nombre) {
        this();
        this.nombre = nombre;
        System.out.println(this + ", ha entrado al juego");
    }

    protected Jugador() {
        mano = new ArrayList<Carta>();
        jugadores.add(this);
    }

    public void pedirCarta(){
        Carta carta = BarajaInglesa54.getBarajaInglesa54().tomarCartaSuperior();
        carta.voltear();
        mano.add(carta);
        System.out.println(this + " pidió una carta");
    }

    public ArrayList<Carta> getMano(){
        return mano;
    }

    @Override
    public String toString() {
        return "Jugador " + jugadores.indexOf(this)  + " : " + nombre;
    }

    public int[] getPuntajeMano(){
        int[] puntaje = {0,0};
        for (Carta carta: mano) {
            if (!carta.getBocaAbajo()) puntaje[0] += carta.getValor();
            else puntaje[1]++;
        }
        return puntaje;
    }
}
