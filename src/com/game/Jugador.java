package com.game;

import com.game.Baraja.BarajaInglesa54;
import com.game.Baraja.Carta;
import com.util.Consola;

import java.util.ArrayList;

public abstract class Jugador {

    protected ArrayList<Carta> mano;
    protected int puntuacionCartasVisiblesMano;
    protected int puntuacionCartasOcultasMano;
    protected int numeroDeCartasOcultas;
    protected JugadorEstado estado;
    protected String nombre;

    public static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

    protected Jugador() {
        jugadores.add(this);
        mano = new ArrayList<Carta>();
        puntuacionCartasVisiblesMano = 0;
        puntuacionCartasOcultasMano = 0;
        numeroDeCartasOcultas = 0;
        estado = JugadorEstado.TOMAR_CARTAS;
    }

    //El constructor con parametro nombre imprime en consola porloque crear otro constructor en subclase si quiere mostrar su propio mensaje
    protected Jugador(String nombre) {
        this();
        this.nombre = nombre;
        System.out.println(this + Consola.Color.PURPLE + " ha entrado al juego");
    }

    public void pedirCarta() {
        Carta carta = BarajaInglesa54.getBarajaInglesa54().tomarCartaSuperior();
        carta.voltear();
        mano.add(carta);
        System.out.println(this + " pidió una carta | " + carta + " |");
    }

    public void pedirCartas(int cantidad) {
        Carta[] cartas = BarajaInglesa54.getBarajaInglesa54().tomarCartasSuperiores(cantidad);
        for (Carta carta : cartas) {
            carta.voltear();
            mano.add(carta);
        }

        System.out.print(this + " pidió " + cantidad + " cartas : | ");
        for (Carta carta : cartas) System.out.print(carta + " | ");
        System.out.println();
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setPuntuacionMano(int puntuacionCartasVisiblesMano, int puntuacionCartasOcultasMano) {
        this.puntuacionCartasVisiblesMano = puntuacionCartasVisiblesMano;
        this.puntuacionCartasOcultasMano = puntuacionCartasOcultasMano;
    }

    public int getPuntuacionMano() {
        return puntuacionCartasVisiblesMano + puntuacionCartasOcultasMano;
    }

    public int getPuntuacionCartasVisiblesMano() {
        return puntuacionCartasVisiblesMano;
    }

    public int getPuntuacionCartasOcultasMano() {
        return puntuacionCartasOcultasMano;
    }

    public int getNumeroDeCartasMano() {
        return mano.size();
    }

    public int getNumeroDeCartasOcultas() {
        return numeroDeCartasOcultas;
    }

    public void setNumeroDeCartasOcultas(int numeroDeCartasOcultas) {
        this.numeroDeCartasOcultas = numeroDeCartasOcultas;
    }

    public JugadorEstado getEstado() {
        return estado;
    }

    public void setEstado(JugadorEstado estado) {
        this.estado = estado;
        switch (estado) {
            case TOMAR_CARTAS:
                System.out.println(this + " ha entrado al juego");
                break;
            case PLANTAR:
                System.out.println(Consola.Color.PURPLE + this + " se ah plantado con " + getPuntuacionMano() + " puntos" + Consola.Color.RESET);
                break;
            case GANAR_CON_BLACK_JACK:
                System.out.println(Consola.Color.GREEN + this + " ha ganado con blackjack" + Consola.Color.RESET);
                break;
            case GANAR_CON_21:
                System.out.println(Consola.Color.GREEN + this + " ha ganado con 21 puntos" + Consola.Color.RESET);
                break;
            case PERDER_CON_MASDE_21:
                System.out.println(Consola.Color.RED + this + " ha perdido con mas de 21 puntos ( " + getPuntuacionMano() + " ) " + Consola.Color.RESET);
                break;
            case GANAR_POR_TENER_5_CARTAS_SIN_PASARSE_DE_21:
                System.out.println(Consola.Color.GREEN + this + " ha ganado por tener 5 cartas sin pasarse de 21 puntos" + Consola.Color.RESET);
                break;
            case TABLAS_CON_CROUPIER:
                System.out.println(Consola.Color.YELLOW + this + " ha hecho tablas con croupier" + Consola.Color.RESET);
                break;
            case GANAR_POR_PUNTOS_CONTRA_CROUPIER:
                System.out.println(Consola.Color.GREEN + this + " ha ganado por puntos contra croupier" + Consola.Color.RESET);
                break;
            case PERDER_POR_PUNTOS_CONTRA_CROUPIER:
                System.out.println(Consola.Color.RED + this + " ha perdido por puntos contra croupier" + Consola.Color.RESET);
                break;
            case GANAR_PORQUE_CROUPIER_PERDER_CON_MAS_DE_21_PUNTOS:
                System.out.println(Consola.Color.GREEN + this + " ha ganado porque croupier ha perdido con mas de 21 puntos" + Consola.Color.RESET);
                break;
            case PERDER_PORQUE_CROUPIER_GANAR_CON_21_PUNTOS:
                System.out.println(Consola.Color.RED + this + " ha perdido porque croupier ha ganado con 21 puntos" + Consola.Color.RESET);
                break;
            case PERDER_PORQUE_CROUPIER_GANAR_CON_BLACK_JACK:
                System.out.println(Consola.Color.RED + this + " ha perdido porque croupier ha ganado con blackjack" + Consola.Color.RESET);
                break;
        }
    }

    public void reset() {
        mano = new ArrayList<Carta>();
        puntuacionCartasOcultasMano = 0;
        puntuacionCartasVisiblesMano = 0;
        estado = JugadorEstado.TOMAR_CARTAS;
    }

    @Override
    public String toString() {
        return "Jugador " + nombre;
    }

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
        System.out.println(puntuacion + " puntos" + Consola.Color.RESET + " : " + getEstado() + " ]");
    }


}
