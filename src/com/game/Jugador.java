package com.game;

import com.game.Baraja.BarajaInglesa54;
import com.game.Baraja.Carta;

import java.util.ArrayList;

public class Jugador {

    private ArrayList<Carta> mano;
    private JugadorEstado estado;
    private String nombre;
    private JugadorAccion accion;
    public static ArrayList<Jugador> jugadores = new ArrayList<>();

    public Jugador(String nombre) {
        this();
        this.nombre = nombre;
        System.out.println(this + ", ha entrado al juego");
    }


    protected Jugador() {
        mano = new ArrayList<Carta>();
        estado = JugadorEstado.jugar;
        accion = JugadorAccion.tomarCarta;
        jugadores.add(this);
    }

    public void pedirCarta() {
        Carta carta = BarajaInglesa54.getBarajaInglesa54().tomarCartaSuperior();
        carta.voltear();
        mano.add(carta);
        System.out.println(this + " pidió una carta");
    }

    public ArrayList<Carta> getMano() { return mano; }

    public JugadorEstado getEstado() {return estado; }

    public void setEstado(JugadorEstado estado) {
        this.estado = estado;
        switch (estado) {
            case jugar:
                System.out.println(this + " ha entrado al juego ");
                break;
            case ganarCon21:
                System.out.println(this + " ha ganado con 21 puntos");
                break;
            case perderConMasde21:
                System.out.println(this + " ha perdido con mas de 21 puntos");
                break;
            case tablasConCroupier:
                System.out.println(this + " ha hecho tablas con croupier ");
                break;
            case ganarPorPuntosContraCroupier:
                System.out.println(this + " ha ganado por puntos contra croupier");
                break;
            case perderPorPuntosContraCroupier:
                System.out.println(this + " ha perdido por puntos contra croupier");
                break;
            case ganarPorqueCroupierPerderConMasDe21Puntos:
                System.out.println(this + " ha ganado porque croupier ha perdido con mas de 21 puntos");
                break;
            case perderPorqueCroupierGanarCon21Puntos:
                System.out.println(this + " ha perido porque croupier ha ganado con 21 puntos");
                break;
        }
    }

    public void setAccion(JugadorAccion accion) {
        this.accion = accion;
    }

    public JugadorAccion getAccion() {
        return accion;
    }

    @Override
    public String toString() {
        return "Jugador " + nombre;
    }

}
