package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest extends TestCase {

    private IPokemonMetadataProvider metadataProvider;
    private PokemonLoader pokemonLoader = new PokemonLoader();
    private List<Pokemon> pokemons ;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        metadataProvider = mock(IPokemonMetadataProvider.class);
        pokemons = pokemonLoader.loadPokemons("pokedexfile");
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        for (Pokemon pokemon : pokemons) {
            int index = pokemon.getIndex();
            PokemonMetadata expectedMetadata = new PokemonMetadata(
                    pokemon.getIndex(),
                    pokemon.getName(),
                    pokemon.getAttack(),
                    pokemon.getDefense(),
                    pokemon.getStamina()
            );

            // Simulation du comportement du mock pour chaque Pokémon
            when(metadataProvider.getPokemonMetadata(index)).thenReturn(expectedMetadata);

            // Appel de la méthode à tester
            PokemonMetadata actualMetadata = metadataProvider.getPokemonMetadata(index);

            // Vérification des résultats
            assertNotNull(actualMetadata);
            assertEquals(expectedMetadata.getIndex(), actualMetadata.getIndex());
            assertEquals(expectedMetadata.getName(), actualMetadata.getName());
            assertEquals(expectedMetadata.getAttack(), actualMetadata.getAttack());
            assertEquals(expectedMetadata.getDefense(), actualMetadata.getDefense());
            assertEquals(expectedMetadata.getStamina(), actualMetadata.getStamina());
        }
    }

    @Test
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        int invalidIndex = -1;
        when(metadataProvider.getPokemonMetadata(invalidIndex)).thenThrow(new PokedexException("Invalid index"));

        // Vérification que l'exception est bien levée
        try {
            metadataProvider.getPokemonMetadata(-1);
            fail("Expected a PokedexException to be thrown");
        } catch (PokedexException e) {
            assertEquals("Invalid index", e.getMessage());
        }
    }
}
