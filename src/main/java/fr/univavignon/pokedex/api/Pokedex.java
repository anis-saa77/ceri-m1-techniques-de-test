package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex{
    private final List<Pokemon> pokemons = new ArrayList<>();
    private final PokemonMetadataProvider pokemonMetadataProvider;
    private final PokemonFactory pokemonFactory;

    public Pokedex(PokemonMetadataProvider pokemonMetadataProvider, PokemonFactory pokemonFactory){
        this.pokemonMetadataProvider = pokemonMetadataProvider;
        this.pokemonFactory = pokemonFactory;
    }
    @Override
    public int size() {
        return this.pokemons.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
        return pokemon.getIndex();
    }
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if(id <= 0 || id > 151) {
            throw new PokedexException("Invalid index");
        }
        return this.pokemons.get(id-1);
    }
    @Override
    public List<Pokemon> getPokemons() {
        return this.pokemons;
    }
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> sortedList = new ArrayList<>(this.pokemons);
        sortedList.sort(order);
        return sortedList;
    }
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return this.pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return this.pokemonMetadataProvider.getPokemonMetadata(index);
    }
}
