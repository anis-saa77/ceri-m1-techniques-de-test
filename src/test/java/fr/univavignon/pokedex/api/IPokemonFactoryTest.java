package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokemonFactoryTest extends TestCase {
    private IPokemonFactory pokemonFactory;
    @BeforeEach
    public void setUp() throws Exception {
        pokemonFactory = mock(IPokemonFactory.class);
    }
    @Test
    public void testCreatePokemon() {
        Pokemon bulbizarre = new Pokemon(1, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        int index = 1;
        int cp = 613;
        int hp = 64;
        int dust = 4000;
        int candy = 4;
        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(bulbizarre);

        Pokemon pokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérification des attributs du Pokémon
        assertNotNull(pokemon);
        assertEquals(pokemon, bulbizarre);
        assertEquals(index, pokemon.getIndex());
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
    }
}