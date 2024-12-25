package fr.univavignon.pokedex.api;

/**
 * Factory for creating instances of {@link IPokedex}.
 * This class implements the {@link IPokedexFactory} interface and provides
 * a concrete implementation for creating a Pokedex using the provided metadata provider
 * and Pokemon factory.
 */
public class PokedexFactory implements IPokedexFactory {

    /**
     * Creates a new {@link IPokedex} instance.
     *
     * @param metadataProvider An instance of {@link IPokemonMetadataProvider} used to retrieve
     *                         metadata about Pokemon.
     * @param pokemonFactory   An instance of {@link IPokemonFactory} used to create Pokemon instances.
     * @return A new instance of {@link IPokedex}.
     */
    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        return new Pokedex((PokemonMetadataProvider) metadataProvider, (PokemonFactory) pokemonFactory);
    }
}
