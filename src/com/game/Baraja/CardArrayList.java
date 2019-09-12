package com.game.Baraja;

import java.util.ArrayList;

public class CardArrayList<E> extends ArrayList<E>{

    @Override
    public boolean remove(Object o) {
        boolean op = super.remove(o);
        System.out.println("La baraja tiene " + this.size() + " cartas");
        return op;
    }
}
