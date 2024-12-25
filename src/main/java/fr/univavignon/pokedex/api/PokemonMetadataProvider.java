package fr.univavignon.pokedex.api;

import java.util.List;

/**
 * Implementation of the {@link IPokemonMetadataProvider} interface.
 * Provides metadata about Pokemon by reading data from a file.
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    /**
     * Loader used to retrieve Pokemon metadata from a file.
     */
    private final PokemonLoader pokemonLoader = new PokemonLoader();

    /**
     * List containing metadata for all available Pokemon.
     * Data is loaded from a file named "pokedexfile".
     */
    private final List<PokemonMetadata> pokemonsMetadata = pokemonLoader.loadPokemonsMetadata("pokedexfile");

    /**
     * Retrieves metadata for a specific Pokemon given its index.
     *
     * @param index The index of the Pokemon (1-based).
     * @return The {@link PokemonMetadata} for the specified Pokemon.
     * @throws PokedexException If the index is invalid (not between 1 and 151).
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index < 1 || index > 151) {
            throw new PokedexException("Invalid index");
        }
        // Adjusting index to match the 0-based list indexing
        return pokemonsMetadata.get(index - 1);
    }
}
