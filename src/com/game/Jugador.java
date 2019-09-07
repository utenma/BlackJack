package com.game;

import com.game.Baraja.BarajaInglesa54;
import com.game.Baraja.Carta;

import java.util.ArrayList;

public class Jugador {

    private ArrayList<Carta> mano;
    private JugadorEstado estado;

    public void setEstado(JugadorEstado estado) {
        this.estado = estado;
    }

    public JugadorEstado getEstado() {
        return estado;
    }

    private String nombre;
    private JugadorAccion accion;
    public static ArrayList<Jugador> jugadores = new ArrayList<>();

    public Jugador(String nombre) {
        this();
        this.nombre = nombre;
        System.out.println(this + ", ha entrado al juego");
    }

    public void setAccion(JugadorAccion accion) {
        this.accion = accion;
    }

    public JugadorAccion getAccion() {
        return accion;
    }

    protected Jugador() {
        mano = new ArrayList<Carta>();
        estado = JugadorEstado.jugar;
        accion = JugadorAccion.tomarCarta;
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
        return "Jugador " + nombre;
    }

}
