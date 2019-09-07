package com.game;

import com.game.Baraja.BarajaInglesa54;
import com.game.Baraja.Carta;
import com.game.Baraja.Rango;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Random;

public class BlackJack {
    private static ArrayList<Jugador> jugadoresEnJuego;

    public static void main(String[] args) {
        /*       System.out.println("Enum de palo");
        for(Palo i : Palo.values()) System.out.println(i.name() + " : "+ i.getPalo());

        System.out.println();
        System.out.println("Enum de rango");
        for(Rango i : Rango.values()) System.out.println(i.name() + " : "+ i.getValor());*/

        jugadoresEnJuego = new ArrayList<Jugador>();
        BarajaInglesa54 baraja = BarajaInglesa54.getBarajaInglesa54();
        baraja.barajar();
        System.out.println();
        Croupier croupier = Croupier.getCroupier();
        jugadoresEnJuego.add(new Jugador("Eduardo"));
        jugadoresEnJuego.add(new Jugador("Daniel"));
        jugadoresEnJuego.add(new Jugador("Victor"));
        System.out.println();

        gameLoop();
    }

    private static void gameLoop() {
        byte ronda = 0;
        boolean finished = false;
        BarajaInglesa54 baraja = BarajaInglesa54.getBarajaInglesa54();
        Croupier croupier = Croupier.getCroupier();

        //todo generar rondas en metodo aparte
        while (!finished) {
            System.out.println("Ronda " + (++ronda));
            System.out.println("La baraja tiene " + baraja.getCantidadDeCartas() + " cartas");
            System.out.println("Cantidad de jugadores " + jugadoresEnJuego.size());
            System.out.println();

            decidirSiTomarCarta(croupier);
            revisarCartasDelJugador(croupier);
            System.out.println();

            if (croupier.getEstado() != JugadorEstado.jugar) {
                break;
            }
            for (int i = 0; i < jugadoresEnJuego.size(); i++) {
                Jugador jugador = jugadoresEnJuego.get(i);
                decidirSiTomarCarta(jugador);
                revisarCartasDelJugador(jugador);
                System.out.println();
            }
            if (Jugador.jugadores.size() == 0) finished = true;

            boolean tomaronCartas = false;
            for (Jugador jugador : jugadoresEnJuego) {
                if (jugador.getAccion() == JugadorAccion.tomarCarta) {
                    tomaronCartas = true;
                    break;
                }
            }
            if (!tomaronCartas) {
                System.out.println("Nadie tomó Cartas, se terminan las rondas");
                break;
            }
        }

        //todo revisar quienes ganan de los jugadores y juego o crupier con las cartas restantes
    }

    //todo check joker
    public static void revisarCartasDelJugador(Jugador jugador) {
        int[] puntaje = calcularPuntajeMano(jugador);

        int puntajeCartasBocaArriba = puntaje[0];
        int puntajeCartasBocaAbajo = puntaje[1];
        int numeroDeCartasOcultas = puntaje[2];

        int puntajeTotal = puntajeCartasBocaArriba + puntajeCartasBocaAbajo;

        if (numeroDeCartasOcultas > 0) {
            System.out.print(jugador + " tiene en su mano de " + (puntajeCartasBocaArriba + numeroDeCartasOcultas * 1) + " a " + (puntajeCartasBocaArriba + numeroDeCartasOcultas* 11) + " puntos");
            System.out.println(" ( " + puntajeTotal + " puntos )");
        } else {
            System.out.println(jugador + " tiene en su mano " + puntajeCartasBocaArriba + " puntos");
        }

        jugador.getMano().forEach(carta -> System.out.println(carta));

        // todo put set estado y print en set ha ganado p perdido
        if (puntajeTotal == 21) jugador.setEstado(JugadorEstado.ganar);
        else if (puntajeTotal > 21)  jugador.setEstado(JugadorEstado.perder);

        if (jugador.getEstado()== JugadorEstado.ganar) {
            System.out.println(jugador + " ha ganado ");
            jugadoresEnJuego.remove(jugador);
        } else if (jugador.getEstado() == JugadorEstado.perder) {
            System.out.println(jugador + " ha perdido ");
            jugadoresEnJuego.remove(jugador);
        }
    }

    public static int[] calcularPuntajeMano(Jugador jugador) {
        //Declaracion sumatorias de puntaje
        int puntajeCartasBocaArriba = 0;
        int puntajeCartasBocaAbajo = 0;
        //Contador de cartas ocultas
        int numeroDeCartasOcultas = 0;

        ArrayList<Carta> mano = jugador.getMano();
        ArrayList<Carta> jokers = new ArrayList<Carta>();

        for (Carta carta : mano) {
            //Revisa si la carta es un joker, si lo es, lo guarda en la lista de jokers para procesar si su puntaje vale 1 u 11 al final de todas las cartas
            if (carta.getRango().getValor() == 1) jokers.add(carta);
            else if (carta.getBocaAbajo()) { //Revisa si la carta está boca abajo
                numeroDeCartasOcultas++; //Cuenta una carta boca abajo
                puntajeCartasBocaAbajo += carta.getValor(); //Suma el valor a la sumatoria de cartas boca abajo
            } else puntajeCartasBocaArriba += carta.getValor(); //Suma el valor a la sumatoria de cartas boca arriba
        }

        for (Carta joker : jokers) {
            int puntaje;
            //Asignar puntaje requerido al Joker
            if (puntajeCartasBocaArriba <= 10) puntaje = 11;
            else puntaje = 1;
            //Asignar puntaje del joker al la sumatoria de puntaje correspondiente
            if (joker.getBocaAbajo()) puntajeCartasBocaAbajo += puntaje;
            else puntajeCartasBocaArriba += puntaje;
        }
        int[] puntajes = {puntajeCartasBocaArriba, puntajeCartasBocaAbajo, numeroDeCartasOcultas};
        return puntajes;
    }


    public static void decidirSiTomarCarta(Jugador jugador) {
        int[] puntajes = calcularPuntajeMano(jugador);
        int puntaje = puntajes[0] + puntajes[1];
        if (puntaje <= 11) {
            jugador.setAccion(JugadorAccion.tomarCarta);
            jugador.pedirCarta();
        } else if (puntaje > 11 && puntaje <= 16) {
            Random random = new Random();
            if (random.nextBoolean()) {
                jugador.setAccion(JugadorAccion.tomarCarta);
                jugador.pedirCarta();
            }
        } else jugador.setAccion(JugadorAccion.noTomarCarta);
        ;
    }
}
