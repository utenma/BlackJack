package com.game;

import com.util.Consola;

import java.util.ArrayList;

public class JugadorBlackJack extends Jugador {

    private int fichas;

    public static ArrayList<JugadorBlackJack> jugadoresBlackJack = new ArrayList<JugadorBlackJack>();

    public static JugadorBlackJack crearJugador(String nombre, int fichas) {
        if (jugadoresBlackJack.size() < 3) {
            JugadorBlackJack jugadorBlackJack = new JugadorBlackJack(nombre, fichas);
            return jugadorBlackJack;
        } else {
            System.out.println("Ya existen 4 jugadores en juego");
            return null;
        }
    }

    private JugadorBlackJack(String nombre, int fichas) {
        super();
        jugadoresBlackJack.add(this);
        this.nombre = nombre;
        this.fichas = fichas;
        System.out.println(this + Consola.Color.PURPLE + " ha entrado al juego con " +
                Consola.Color.YELLOW + fichas + " fichas " + Consola.Color.RESET);
    }

    @Override
    public void setEstado(JugadorEstado estado) {
        super.setEstado(estado);
        this.estado = estado;
        switch (estado) {
            case GANAR_CON_BLACK_JACK:
                ganarFicha();
                break;
            case GANAR_CON_21:
                ganarFicha();
                break;
            case PERDER_CON_MASDE_21:
                perderFicha();
                break;
            case GANAR_POR_TENER_5_CARTAS_SIN_PASARSE_DE_21:
                ganarFicha();
                break;
            case TABLAS_CON_CROUPIER:
                recuperarFicha();
                break;
            case GANAR_POR_PUNTOS_CONTRA_CROUPIER:
                ganarFicha();
                break;
            case PERDER_POR_PUNTOS_CONTRA_CROUPIER:
                perderFicha();
                break;
            case GANAR_PORQUE_CROUPIER_PERDER_CON_MAS_DE_21_PUNTOS:
                ganarFicha();
                break;
            case PERDER_PORQUE_CROUPIER_GANAR_CON_21_PUNTOS:
                perderFicha();
                break;
            case PERDER_PORQUE_CROUPIER_GANAR_CON_BLACK_JACK:
                perderFicha();
                break;
        }
    }

    public void perderFicha() {
        fichas--;
        System.out.println(this + " perdió la ficha apostada : " + Consola.Color.RED + fichas + " restantes" + Consola.Color.RESET);
        if(fichas==0) System.out.println(this + " ha salido del juego");
    }

    public void recuperarFicha() {
        System.out.println(this + " recuperar la ficha apostada : " + Consola.Color.YELLOW + fichas + " restantes" + Consola.Color.RESET);
    }

    public void ganarFicha() {
        fichas++;
        System.out.println(this + " ha ganado dos fichas : " + Consola.Color.GREEN + fichas + " restantes" + Consola.Color.RESET);
    }

    public int getFichas() {
        return fichas;
    }

    @Override
    public void mostrarDatos(){
        System.out.print("[ " + this + " : ");
        int puntuacion = getPuntuacionMano();
        if (puntuacion > 21) {
            System.out.print(Consola.Color.RED);
        } else if (puntuacion > 17 && puntuacion <= 21) {
            System.out.print(Consola.Color.GREEN);
        } else {
            System.out.print(Consola.Color.YELLOW);
        }
        System.out.println(puntuacion + " puntos" + Consola.Color.RESET + " : " + getEstado() + " : " + getFichas() + " fichas ]");
    }

}