package com.game;

import com.util.Consola;

// TODO: 9/9/2019 change typo to spanish
public enum JugadorEstado {
    TOMAR_CARTAS("Puede tomar cartas"),
    PLANTAR("Plantado"),
    GANAR_CON_BLACK_JACK(Consola.Color.GREEN + "Ganó con BlackJack" + Consola.Color.RESET),
    GANAR_CON_21(Consola.Color.GREEN +"Ganó con 21" + Consola.Color.RESET),
    PERDER_CON_MASDE_21(Consola.Color.RED + "Perdió con mas de 21" + Consola.Color.RESET),
    GANAR_POR_TENER_5_CARTAS_SIN_PASARSE_DE_21(Consola.Color.GREEN + "Ganó por tener 5 cartas y no pasarde de 21 puntos" + Consola.Color.RESET),
    TABLAS_CON_CROUPIER(Consola.Color.YELLOW + "Tablas Con Cruoupier" + Consola.Color.RESET),
    GANAR_POR_PUNTOS_CONTRA_CROUPIER(Consola.Color.GREEN + "Ganó contra Croupier por puntos" + Consola.Color.RESET),
    PERDER_POR_PUNTOS_CONTRA_CROUPIER(Consola.Color.RED + "Perdió contra Croupier por puntos" + Consola.Color.RESET),
    GANAR_PORQUE_CROUPIER_PERDER_CON_MAS_DE_21_PUNTOS(Consola.Color.GREEN + "Ganó porque Croupier se pasó de los 21 puntos" + Consola.Color.RESET),
    PERDER_PORQUE_CROUPIER_GANAR_CON_21_PUNTOS(Consola.Color.RED + "Perdió porque croupier ganó con 21 puntos" + Consola.Color.RESET),
    PERDER_PORQUE_CROUPIER_GANAR_CON_BLACK_JACK(Consola.Color.RED + "Perdió porque croupier ganó con BlackJack" + Consola.Color.RESET);

    private String estado;

    JugadorEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return estado;
    }
    
}
