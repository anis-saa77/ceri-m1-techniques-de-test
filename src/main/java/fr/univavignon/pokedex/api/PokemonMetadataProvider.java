package fr.univavignon.pokedex.api;

import java.util.List;

public class PokemonMetadataProvider implements IPokemonMetadataProvider{
    PokemonLoader pokemonLoader = new PokemonLoader();
    List<PokemonMetadata> pokemonsMetadata = pokemonLoader.loadPokemonsMetadata("pokedexfile");
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index < 1 || index > 151){
            throw new PokedexException("Invalid index");
        }
        return pokemonsMetadata.get(index-1);
    }
}
