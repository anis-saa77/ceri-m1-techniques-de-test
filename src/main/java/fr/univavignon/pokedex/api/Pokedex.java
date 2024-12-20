package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * La classe {@code Pokedex} implémente l'interface {@link IPokedex} et représente un Pokedex
 * contenant une liste de Pokémon. Elle permet d'ajouter, récupérer et trier les Pokémon,
 * ainsi que de créer des instances de Pokémon et récupérer leurs métadonnées.
 *
 * <p>Le Pokedex utilise un fournisseur de métadonnées de Pokémon et un fabriquant de Pokémon
 * pour créer des Pokémon et obtenir leurs informations.</p>
 *
 * @see IPokedex
 * @see Pokemon
 * @see PokemonMetadata
 */
public class Pokedex implements IPokedex {

    /** Liste des Pokémon contenus dans ce Pokedex */
    private final List<Pokemon> pokemons = new ArrayList<>();

    /** Fournisseur de métadonnées de Pokémon */
    private final PokemonMetadataProvider pokemonMetadataProvider;

    /** Fabriquant de Pokémon */
    private final PokemonFactory pokemonFactory;

    /**
     * Constructeur de la classe {@code Pokedex}.
     *
     * @param pokemonMetadataProvider le fournisseur de métadonnées de Pokémon
     * @param pokemonFactory le fabriquant de Pokémon
     */
    public Pokedex(PokemonMetadataProvider pokemonMetadataProvider, PokemonFactory pokemonFactory){
        this.pokemonMetadataProvider = pokemonMetadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    /**
     * Retourne le nombre de Pokémon présents dans le Pokedex.
     *
     * @return le nombre de Pokémon dans le Pokedex
     */
    @Override
    public int size() {
        return this.pokemons.size();
    }

    /**
     * Ajoute un Pokémon au Pokedex.
     *
     * @param pokemon le Pokémon à ajouter
     * @return l'index du Pokémon ajouté
     */
    @Override
    public int addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
        return pokemon.getIndex();
    }

    /**
     * Récupère un Pokémon à partir de son index.
     *
     * @param id l'index du Pokémon à récupérer
     * @return le Pokémon correspondant à l'index spécifié
     * @throws PokedexException si l'index est invalide
     */
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if(id <= 0 || id > 151) {
            throw new PokedexException("Invalid index");
        }
        return this.pokemons.get(id - 1);
    }

    /**
     * Retourne la liste de tous les Pokémon présents dans le Pokedex.
     *
     * @return une liste de tous les Pokémon
     */
    @Override
    public List<Pokemon> getPokemons() {
        return this.pokemons;
    }

    /**
     * Retourne une liste de Pokémon triée selon le comparateur spécifié.
     *
     * @param order le comparateur utilisé pour trier la liste des Pokémon
     * @return une nouvelle liste triée des Pokémon
     */
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> sortedList = new ArrayList<>(this.pokemons);
        sortedList.sort(order);
        return sortedList;
    }

    /**
     * Crée un Pokémon avec les attributs spécifiés.
     *
     * @param index l'index du Pokémon
     * @param cp les points de combat du Pokémon
     * @param hp les points de vie du Pokémon
     * @param dust les poussières de Stardust nécessaires pour le Pokémon
     * @param candy les bonbons nécessaires pour le Pokémon
     * @return un nouveau Pokémon créé
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return this.pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    /**
     * Récupère les métadonnées d'un Pokémon à partir de son index.
     *
     * @param index l'index du Pokémon dont on souhaite récupérer les métadonnées
     * @return les métadonnées du Pokémon spécifié
     * @throws PokedexException si une erreur se produit lors de la récupération des métadonnées
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return this.pokemonMetadataProvider.getPokemonMetadata(index);
    }
}
