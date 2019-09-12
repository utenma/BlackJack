package com.game;

import com.game.Baraja.BarajaInglesa54;
import com.game.Baraja.Carta;
import com.game.Baraja.CartaRango;
import com.util.Consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

//todo Poner regla que croupier se planta hasta hasta 17
// TODO: 9/11/2019   while cartas joker por for
//todo Deberia haber clase base de la que hereden jugador y croupier para tener conteo independiente

public class BlackJack {
    private static ArrayList<JugadorBlackJack> jugadoresBlackJackQuePuedenTomarCartas;
    private static Croupier croupier;
    private static BarajaInglesa54 baraja;
    private static BufferedReader bufferedReader;
    private static boolean autoData = false;
    private static int ronda = 0;

    public static void main(String[] args) {
        croupier = Croupier.getCroupier();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        if (autoData) {
            JugadorBlackJack.crearJugador("Eduardo", 2);
            JugadorBlackJack.crearJugador("Misael", 2);
            JugadorBlackJack.crearJugador("Victor", 2);
            System.out.println();
        } else {
            int numeroDeJugadores = 0;
            System.out.print("Ingrese numero de jugadores: ");
            numeroDeJugadores = leerEnteroDiferenteDeCero();
            numeroDeJugadores = (numeroDeJugadores > 3) ? 3 : numeroDeJugadores;
            numeroDeJugadores = (numeroDeJugadores < 0) ? 1 : numeroDeJugadores;
            System.out.println("EL juego tendrá " + numeroDeJugadores + " jugadores");
            jugadoresBlackJackQuePuedenTomarCartas = new ArrayList<JugadorBlackJack>();
            for (int i = 1; i <= numeroDeJugadores; i++) {
                System.out.println("Datos jugador: " + i + " de " + numeroDeJugadores);
                System.out.print("Nombre : ");
                try {
                    String nombre = bufferedReader.readLine();
                    System.out.print("Fichas : ");
                    int numeroFichas = leerEnteroDiferenteDeCero();
                    JugadorBlackJack jugadorBlackJack = JugadorBlackJack.crearJugador(nombre, numeroFichas);
                    jugadoresBlackJackQuePuedenTomarCartas.add(jugadorBlackJack);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println();
            }
        }
        gameLoop();
    }

    private static int leerEnteroDiferenteDeCero() {
        int entero = 0;
        do {
            try {
                String entrada = bufferedReader.readLine();
                entero = Integer.parseInt(entrada);
            } catch (Exception e) {
                entero = 0;
                System.out.println("Se debe ingresar un numero entero diferente de cero.");
            }
        } while (entero == 0);
        return entero;
    }

    private static void gameLoop() {
        boolean quedanJugadores = false;
        baraja = BarajaInglesa54.getBarajaInglesa54();
        baraja.barajar();

        do {
            //Eliminar Jugadores sin fichas
            for (int i = 0; i < JugadorBlackJack.jugadoresBlackJack.size(); i++) {
                JugadorBlackJack jugadorBlackJack = JugadorBlackJack.jugadoresBlackJack.get(i);
                if (jugadorBlackJack.getFichas() == 0) {
                    JugadorBlackJack.jugadoresBlackJack.remove(jugadorBlackJack);
                    Jugador.jugadores.remove(jugadorBlackJack);
                }
            }

            System.out.println();
            jugadoresBlackJackQuePuedenTomarCartas = new ArrayList<JugadorBlackJack>();
            //Añadir los jugadores blackJack a la lista de jugadores activos ( que no han ganado ni perdido )
            JugadorBlackJack.jugadoresBlackJack.forEach(jugadorBlackJack -> jugadoresBlackJackQuePuedenTomarCartas.add(jugadorBlackJack));

            System.out.println("| " + Consola.Color.PURPLE + "Ronda " + ++ronda + Consola.Color.RESET + " | " +
                    Consola.Color.PURPLE + JugadorBlackJack.jugadoresBlackJack.size() + " jugador(es)" + Consola.Color.RESET +
                    " | " + Consola.Color.PURPLE + baraja.getCantidadDeCartas() + " cartas" + Consola.Color.RESET + " |");

            JugadorBlackJack.jugadoresBlackJack.stream().forEach(jugadorBlackJack -> {
                System.out.println(jugadorBlackJack + " ah apostado una ficha : " + Consola.Color.YELLOW + (jugadorBlackJack.getFichas() - 1) + " restantes" + Consola.Color.RESET);
            });

            System.out.println();

            //Darle dos cartas a cada 1
            for (Jugador jugador : Jugador.jugadores) {
                jugador.pedirCartas(2);
                calcularPuntajesMano(jugador);
                imprimirCartasJugador(jugador);
                if (jugador.getPuntuacionMano() == 21) {
                    if (jugador == croupier) {
                        croupier.setEstado(JugadorEstado.GANAR_CON_BLACK_JACK);
                        for (JugadorBlackJack jugadorBlackJack : jugadoresBlackJackQuePuedenTomarCartas) {
                            jugadorBlackJack.setEstado(JugadorEstado.PERDER_PORQUE_CROUPIER_GANAR_CON_BLACK_JACK);
                        }
                        break; // ya no reparte dos cartas a los demas jugadores
                    } else comprobarPuntajeDelJugador(jugador);
                }
                System.out.println();
                pause(2000);
            }
            if (croupier.getEstado() != JugadorEstado.GANAR_CON_BLACK_JACK) {
                System.out.println("-----------------------------");
                System.out.println(Consola.Color.PURPLE + "Repartición inicial terminada" + Consola.Color.RESET);
                System.out.println("-----------------------------");
                System.out.println(Consola.Color.PURPLE + "Es turno de los " + jugadoresBlackJackQuePuedenTomarCartas.size() + " jugadores de pedir cartas" + Consola.Color.RESET);
                System.out.println("-----------------------------");
                System.out.println();

                //Hasta que todos jugadores dejen de tomar cartas osea que todos ganen pierdan o se planten
                while (jugadoresBlackJackQuePuedenTomarCartas.size() > 0) {
                    JugadorBlackJack jugadorBlackJack = jugadoresBlackJackQuePuedenTomarCartas.get(0);
                    System.out.println("Turno del 1" + jugadorBlackJack + " de pedir cartas");
                    boolean tomaraCarta = decidirSiTomarCarta(jugadorBlackJack);
                    while (jugadorBlackJack.getEstado() == JugadorEstado.TOMAR_CARTAS) {
                        if (tomaraCarta) {
                            calcularPuntajesMano(jugadorBlackJack);
                            imprimirCartasJugador(jugadorBlackJack);
                            comprobarPuntajeDelJugador(jugadorBlackJack);
                        }
                        if (jugadorBlackJack.getEstado() == JugadorEstado.TOMAR_CARTAS) {
                            tomaraCarta = decidirSiTomarCarta(jugadorBlackJack);
                        }
                        System.out.println();
                    }
                    pause(2000);
                }

                System.out.println();
                List<JugadorBlackJack> finalistas = JugadorBlackJack.jugadoresBlackJack.stream().filter(jugadorBlackJack -> jugadorBlackJack.getEstado() == JugadorEstado.PLANTAR).collect(Collectors.toList());

                //Revisar si hay algun jugador plantado ( que no ha ganado ni perdido )
                if (finalistas.size() > 0) {
                    System.out.println("Los jugadores han terminado de tomar cartas, es el turno de croupier");
                    System.out.println();

                    while (croupier.getEstado() == JugadorEstado.TOMAR_CARTAS) {
                        if (decidirSiTomarCarta(croupier)) {
                            calcularPuntajesMano(croupier);
                            imprimirCartasJugador(croupier);
                            comprobarPuntajeDelJugador(croupier);
                            System.out.println();
                        }
                    }

                    if (croupier.getEstado() == JugadorEstado.PLANTAR) {
                        System.out.println("-----------------------------");
                        System.out.println(Consola.Color.PURPLE + "Ahora croupier mostrará todas sus cartas" + Consola.Color.RESET);
                        System.out.println("-----------------------------");
                        for (Carta carta : croupier.getMano()) if (carta.getBocaAbajo()) carta.voltear();
                        imprimirCartasJugador(croupier);
                        System.out.println();
                    }
                }
                compararPuntajesDeFinalistas(finalistas);
            }

            System.out.println();
            System.out.println("Resumen de resultados");
            Jugador.jugadores.forEach(jugador -> jugador.mostrarDatos());
            System.out.println();

            quedanJugadores = JugadorBlackJack.jugadoresBlackJack.stream().anyMatch(jugadorBlackJack -> (jugadorBlackJack.getFichas() > 0));
            if (quedanJugadores) {
                Jugador.jugadores.forEach(jugador -> jugador.reset());
                baraja.inicializar();
                baraja.barajar();
            }
        } while (quedanJugadores);
        System.out.println();
        System.out.println("Los jugadores se han quedado sin fichas");
    }

    public static void pause(int timeInMiliseconds) {
        try {
            Thread.sleep(timeInMiliseconds);
        } catch (InterruptedException e) {
        }
    }

    public static boolean decidirSiTomarCarta(Jugador jugador) {
        if (jugador.getPuntuacionMano() <= 11) {
            jugador.pedirCarta();
            return true;
        } else if (jugador.getPuntuacionMano() > 11 && jugador.getPuntuacionMano() <= 16) {
            Random random = new Random();
            boolean desicion = true;
            if (desicion) jugador.pedirCarta();
            return desicion;
        } else {
            jugador.setEstado(JugadorEstado.PLANTAR);
            System.out.println();
            jugadoresBlackJackQuePuedenTomarCartas.remove(jugador);
            return false;
        }
    }

    public static void imprimirCartasJugador(Jugador jugador) {
        String color;
        if (jugador.getPuntuacionMano() > 21) {
            color = Consola.Color.RED;
        } else if (jugador.getPuntuacionMano() > 17 && jugador.getPuntuacionMano() <= 21) {
            color = Consola.Color.GREEN;
        } else {
            color = Consola.Color.YELLOW;
        }

        if (jugador.getNumeroDeCartasOcultas() > 0) {
            System.out.print(jugador + " tiene en su mano de " + (jugador.getPuntuacionCartasVisiblesMano() + jugador.getNumeroDeCartasOcultas() * 1) + " a " + (jugador.getPuntuacionCartasVisiblesMano() + jugador.getNumeroDeCartasOcultas() * 11) + " puntos");
            System.out.println(" ( " + color + jugador.getPuntuacionMano() + Consola.Color.RESET + " puntos )");
        } else {
            System.out.println(jugador + " tiene en su mano " + color + jugador.getPuntuacionMano() + Consola.Color.RESET + " puntos");
        }

        jugador.getMano().forEach(carta -> {
            System.out.print("\u270B : " + carta);
            if (carta.getRango() == CartaRango.JOKER1 || carta.getRango() == CartaRango.JOKER_2 || carta.getRango() == CartaRango.AS)
                System.out.print(" : " + carta.getValor() + " puntos");
            System.out.println();
        });
    }

    public static void calcularPuntajesMano(Jugador jugador) {
        int puntajeCartas = 0;
        ArrayList<Carta> jokers = new ArrayList<Carta>();
        ArrayList<Carta> aces = new ArrayList<Carta>();

        for (Carta carta : jugador.getMano()) {
            //Revisa la cartas es especial ( as / joker ) si lo es lo guarda para procesar despues
            if (carta.getRango() == CartaRango.JOKER1 || carta.getRango() == CartaRango.JOKER_2) jokers.add(carta);
            else if (carta.getRango() == CartaRango.AS) aces.add(carta);
            else puntajeCartas += carta.getValor(); //Suma el valor a la sumatoria de cartas boca arriba
        }

        //Se procesan Aces
        // Cada As puede valer de 0 u 11 dependiendo de lo que se ocupe para acercarse a 21 sin pasarse
        //Lo minino que pueden valor los aces es 1, no importa si se pasa de 21
        // Ejemplo para un puntaje = 9 + 3 aces
        // 21 - 9 = 12 maximo
        // 9 + 3 x 11 = 42 | 42 > 12 -> si
        // 10 + 2 x 11 = 23 | 23 > 12 -> si
        // 11 + 1 x 11 = 22 | 22 > 12 -> si
        // 12 + 0 x 11 = 12 | 12 > 12 -> no ok
        for (int i = 0; i < aces.size(); i++) {
            Carta as = aces.get(i);
            if ((puntajeCartas + aces.size() * 11) > 21) {
                puntajeCartas++;
                as.setValorRango(1);
            } else {
                puntajeCartas += 11;
                as.setValorRango(11);
            }
        }

        //Se procesan jokers
        // Cada joker puede valer de 0 a 11 dependiendo de lo que se necesite para llegar a 21 sin pasarse
        if (jokers.size() > 0) {
            int puntajeRestante = 21 - puntajeCartas;
            if (puntajeRestante > 0) {
                for (Carta joker : jokers) {
                    if (puntajeRestante >= 11) {
                        joker.setValorRango(11);
                        puntajeCartas += 11;
                    } else {
                        joker.setValorRango(puntajeRestante);
                        puntajeCartas = 21;
                    }
                }
            }
        }

        //Declaracion sumatorias de puntaje
        int puntajeCartasBocaArriba = 0;
        int puntajeCartasBocaAbajo = 0;
        //Contador de cartas ocultas
        int numeroDeCartasOcultas = 0;

        for (Carta carta : jugador.getMano()) {
            if (carta.getBocaAbajo()) {
                numeroDeCartasOcultas++;
                puntajeCartasBocaAbajo += carta.getValor();
            }
        }
        puntajeCartasBocaArriba = puntajeCartas - puntajeCartasBocaAbajo;

        jugador.setPuntuacionMano(puntajeCartasBocaArriba, puntajeCartasBocaAbajo);
        jugador.setNumeroDeCartasOcultas(numeroDeCartasOcultas);
    }

    /**
     * Cuenta la cantidad de puntos que tiene un jugador y guarda el valor en el objeto jugador
     * de manera que solo se tiene que calcular una vez por ronda
     *
     * @param jugador
     */
    public static void comprobarPuntajeDelJugador(Jugador jugador) {
        if (jugador.getPuntuacionMano() == 21) {
            jugador.setEstado(JugadorEstado.GANAR_CON_21);
            jugadoresBlackJackQuePuedenTomarCartas.remove(jugador);
        } else if (jugador.getNumeroDeCartasMano() == 5 && jugador.getPuntuacionMano() < 21) {
            jugador.setEstado(JugadorEstado.GANAR_POR_TENER_5_CARTAS_SIN_PASARSE_DE_21);
            jugadoresBlackJackQuePuedenTomarCartas.remove(jugador);
        } else if (jugador.getPuntuacionMano() > 21) {
            jugador.setEstado(JugadorEstado.PERDER_CON_MASDE_21);
            jugadoresBlackJackQuePuedenTomarCartas.remove(jugador);
        }
    }

    ///revisar quienes ganan de los jugadores y juego o crupier con las cartas restantes
    public static void compararPuntajesDeFinalistas(List<JugadorBlackJack> finalistas) {
        System.out.println("Revisión de puntajes de finalistas");
        //Si croupier se plantó comprobar los puntos con los jugadores que no ganaron o perdieron en caso de que halla
        if (croupier.getEstado() == JugadorEstado.PLANTAR) {
            int puntosRestantesPara21Croupier = 21 - croupier.getPuntuacionMano();
            for (JugadorBlackJack jugadorBlackJack : finalistas) {
                int puntosRestantesPara21 = 21 - jugadorBlackJack.getPuntuacionMano();
                if (puntosRestantesPara21 < puntosRestantesPara21Croupier)
                    jugadorBlackJack.setEstado(JugadorEstado.GANAR_POR_PUNTOS_CONTRA_CROUPIER);
                else if (puntosRestantesPara21 == puntosRestantesPara21Croupier)
                    jugadorBlackJack.setEstado(JugadorEstado.TABLAS_CON_CROUPIER);
                else jugadorBlackJack.setEstado(JugadorEstado.PERDER_POR_PUNTOS_CONTRA_CROUPIER);
            }
        }
        if (croupier.getEstado() == JugadorEstado.GANAR_CON_21) {
            finalistas.forEach(jugadorBlackJack -> jugadorBlackJack.setEstado(JugadorEstado.PERDER_PORQUE_CROUPIER_GANAR_CON_21_PUNTOS));
        }
        if (croupier.getEstado() == JugadorEstado.PERDER_CON_MASDE_21) {
            finalistas.forEach(jugadorBlackJack -> jugadorBlackJack.setEstado(JugadorEstado.GANAR_PORQUE_CROUPIER_PERDER_CON_MAS_DE_21_PUNTOS));
        }
    }
}
