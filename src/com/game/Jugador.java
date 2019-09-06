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
        Carta carta = Baraja.getBaraja().tomarCartaSuperior();
        carta.voltear();
        mano.add(carta);
    }

    public ArrayList<Carta> getMano(){
        return mano;
    }

    @Override
    public String toString() {
        return "Jugador " + jugadores.indexOf(this)  + " : " + nombre;
    }
}
