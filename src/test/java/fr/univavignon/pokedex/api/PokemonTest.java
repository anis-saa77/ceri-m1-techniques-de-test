package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    Pokemon bulbizarre = new Pokemon(1, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
    @Test
    void getCp() {
        assertEquals(bulbizarre.getCp(), 613);
    }

    @Test
    void getHp() {
        assertEquals(bulbizarre.getHp(), 64);
    }

    @Test
    void getDust() {
        assertEquals(bulbizarre.getDust(), 4000);
    }

    @Test
    void getCandy() {
        assertEquals(bulbizarre.getCandy(), 4);
    }

    @Test
    void getIv() {
        assertEquals(bulbizarre.getIv(), 56);
    }
}