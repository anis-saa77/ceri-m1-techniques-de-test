package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PokedexFactoryTest {

    private PokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;

    @Before
    public void setUp() {
        pokedexFactory = new PokedexFactory();
        metadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory();
    }

    @Test
    public void testCreatePokedex() {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Vérifie que la méthode retourne une instance de Pokedex
        assertNotNull("Pokedex instance should not be null", pokedex);
        assertTrue("Pokedex instance should be of type Pokedex", pokedex instanceof Pokedex);

        // Vérifie que le Pokedex créé est vide
        assertEquals("Pokedex should initially be empty", 0, pokedex.size());

        // Test des dépendances internes
        Pokemon pokemon = pokemonFactory.createPokemon(1, 500, 100, 50, 10);
        pokedex.addPokemon(pokemon);
        assertEquals("Pokedex should contain one Pokemon after addition", 1, pokedex.size());
    }

}