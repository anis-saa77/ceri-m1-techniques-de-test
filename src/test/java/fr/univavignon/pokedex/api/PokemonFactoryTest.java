package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PokemonFactoryTest {
    private PokemonFactory pokemonFactory;
    @Before
    public void setUp() {
        pokemonFactory = new PokemonFactory();
    }
    @Test
    public void testCreatePokemon() {
        // Données d'entrée
        int index = 1;
        int cp = 500;
        int hp = 100;
        int dust = 50;
        int candy = 10;

        // Création du Pokémon
        Pokemon pokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérifications des propriétés
        assertNotNull("The created Pokemon should not be null", pokemon);
        assertEquals("Index should match the input index", index, pokemon.getIndex());
        assertEquals("CP should match the input CP", cp, pokemon.getCp());
        assertEquals("HP should match the input HP", hp, pokemon.getHp());
        assertEquals("Dust should match the input dust", dust, pokemon.getDust());
        assertEquals("Candy should match the input candy", candy, pokemon.getCandy());
        assertEquals("IV should initially be zero", 0, pokemon.getIv(), 0);

        // Vérifications des métadonnées via le metadataProvider
        assertEquals("Name should match metadata", "Bulbizarre", pokemon.getName());
        assertEquals("Attack should match metadata", 126, pokemon.getAttack());
        assertEquals("Defense should match metadata", 126, pokemon.getDefense());
        assertEquals("Stamina should match metadata", 90, pokemon.getStamina());
    }

    @Test(expected = RuntimeException.class)
    public void testCreatePokemonWithInvalidIndex() {
        // Test avec un index invalide
        int invalidIndex = 999;
        pokemonFactory.createPokemon(invalidIndex, 500, 100, 50, 10);
    }
}
