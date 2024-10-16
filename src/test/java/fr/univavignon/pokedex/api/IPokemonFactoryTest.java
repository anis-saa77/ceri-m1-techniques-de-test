package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokemonFactoryTest extends TestCase {
    private IPokemonFactory pokemonFactory;
    PokemonLoader pokemonLoader = new PokemonLoader();
    List<Pokemon> pokemons ;
    @BeforeEach
    public void setUp() throws Exception {
        pokemonFactory = mock(IPokemonFactory.class);
        pokemons = pokemonLoader.loadPokemons("pokedexfile");
    }
    @Test
    public void testCreatePokemon() {
        for(Pokemon loadePokemon : pokemons){
            when(pokemonFactory.createPokemon(loadePokemon.getIndex(), loadePokemon.getCp(), loadePokemon.getHp(), loadePokemon.getDust(), loadePokemon.getCandy())).thenReturn(loadePokemon);
            Pokemon pokemon = pokemonFactory.createPokemon(loadePokemon.getIndex(), loadePokemon.getCp(), loadePokemon.getHp(), loadePokemon.getDust(), loadePokemon.getCandy());

            // Vérification des attributs du Pokémon
            assertNotNull(pokemon);
            assertEquals(loadePokemon, pokemon);
            assertEquals(loadePokemon.getIndex(), pokemon.getIndex());
            assertEquals(loadePokemon.getCp(), pokemon.getCp());
            assertEquals(loadePokemon.getHp(), pokemon.getHp());
            assertEquals(loadePokemon.getDust(), pokemon.getDust());
            assertEquals(loadePokemon.getCandy(), pokemon.getCandy());
        }
    }
}