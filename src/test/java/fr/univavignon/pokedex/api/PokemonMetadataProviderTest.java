package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class PokemonMetadataProviderTest {
    private final PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
    private final PokemonLoader pokemonLoader = new PokemonLoader();
    private List<PokemonMetadata> pokemonsMetadata;
    @Before
    public void setUp() {
        pokemonsMetadata = pokemonLoader.loadPokemonsMetadata("pokedexfile");
    }
    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        int index = 1;
        for (PokemonMetadata expectedMetadata : pokemonsMetadata) {
            // Appel de la méthode à tester
            PokemonMetadata actualMetadata = metadataProvider.getPokemonMetadata(index);

            // Vérification des résultats
            assertNotNull(actualMetadata);
            assertEquals(expectedMetadata.getIndex(), actualMetadata.getIndex());
            assertEquals(expectedMetadata.getName(), actualMetadata.getName());
            assertEquals(expectedMetadata.getAttack(), actualMetadata.getAttack());
            assertEquals(expectedMetadata.getDefense(), actualMetadata.getDefense());
            assertEquals(expectedMetadata.getStamina(), actualMetadata.getStamina());

            index++ ;
        }
    }
    @Test
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        int invalidIndex = -1;
        // Vérification que l'exception est bien levée
        try {
            metadataProvider.getPokemonMetadata(-1);
            fail("Expected a PokedexException to be thrown");
        } catch (PokedexException e) {
            assertEquals("Invalid index", e.getMessage());
        }
    }
}
