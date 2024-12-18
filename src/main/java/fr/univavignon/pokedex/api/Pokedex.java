package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex{
    private List<Pokemon> pokemons = new ArrayList<>();

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
        List<Pokemon> sortedList = new ArrayList<>();
        sortedList.addAll(this.pokemons);
        sortedList.sort(order);
        return sortedList;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
        PokemonMetadata metadata = null;
        try {
            metadata = metadataProvider.getPokemonMetadata(index);
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }
        String name = metadata.getName();
        int attack = metadata.getAttack();
        int defense = metadata.getDefense();
        int stamina = metadata.getStamina();
        int iv = 0;
        return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        Pokemon pokemon = this.getPokemon(index);
        return new PokemonMetadata(pokemon.getIndex(), pokemon.getName(), pokemon.getAttack(), pokemon.getDefense(), pokemon.getStamina());
    }
}
