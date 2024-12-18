package fr.univavignon.pokedex.api;

import org.junit.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
public class PokedexTest{

    private PokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();
    private PokemonFactory pokemonFactory = new PokemonFactory();

;   private Pokedex pokedex = new Pokedex(pokemonMetadataProvider, pokemonFactory);
    private PokemonLoader pokemonLoader = new PokemonLoader();
    private List<Pokemon> pokemons ;

    @Before
    public void setUp() {
        pokemons = pokemonLoader.loadPokemons("pokedexfile");
    }

    @Test
    public void testSize() {
        assertEquals(0, pokedex.size());
        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);
            // Ajouter le Pokémon et vérifier la taille
            pokedex.addPokemon(pokemon);
            assertEquals(i + 1, pokedex.size());
        }
    }
    @Test
    public void testAddPokemon() {
        for (Pokemon pokemon : pokemons) {
            assertEquals(pokemon.getIndex(), pokedex.addPokemon(pokemon));
        }
    }
    @Test
    public void testGetPokemon() throws PokedexException {
        for (Pokemon pokemon : pokemons) {
            int index = pokemon.getIndex();
            pokedex.addPokemon(pokemon);
            assertEquals(pokedex.getPokemon(index), pokemon);
        }
    }
    @Test
    public void testGetPokemonWithInvalidIndex() throws PokedexException {
        int invalidIndex1 = 0;
        int invalidIndex2 = 152;

        //Vérification de la correspondance avec le pokemon voulu
        try {
            pokedex.getPokemon(invalidIndex1);
            fail("Expected a PokedexException to be thrown");
        } catch (PokedexException e) {
            assertEquals("Invalid index", e.getMessage());
        }
        try {
            pokedex.getPokemon(invalidIndex2);
            fail("Expected a PokedexException to be thrown");
        } catch (PokedexException e) {
            assertEquals("Invalid index", e.getMessage());
        }
    }

    @Test
    public void testGetPokemons() {
        List<Pokemon> exceptedPokemons = new ArrayList<>();

        //Ajout des 10 premiers pokemons
        for(int i = 0; i<10; i++){
            Pokemon pokemon = pokemons.get(i);
            pokedex.addPokemon(pokemon);
            exceptedPokemons.add(pokemon);
        }
        assertEquals(pokedex.getPokemons(), exceptedPokemons);
        assertEquals(pokedex.getPokemons().size(), exceptedPokemons.size());

        //Ajout des 50 suivants
        for(int i = 10; i<50; i++){
            Pokemon pokemon = pokemons.get(i);
            pokedex.addPokemon(pokemon);
            exceptedPokemons.add(pokemon);
        }
        assertEquals(pokedex.getPokemons(), exceptedPokemons);
        assertEquals(pokedex.getPokemons().size(), exceptedPokemons.size());
    }
    @Test
    public void testGetPokemonsWithOrder() {
        /** Test ordre par les index **/
        List<Pokemon> sortedPokemons = new ArrayList<>(pokemons); //pokemons est déjà trié selon les indices des pokemons

        //Remplir le pokedex
        for(Pokemon pokemon : pokemons){
            pokedex.addPokemon(pokemon);
        }
        // Appel à getPokemons avec un comparateur qui trie par ordre alphabétique
        List<Pokemon> pokemonsFromPokedex = pokedex.getPokemons(PokemonComparators.INDEX);

        // Vérifications de la taille
        assertEquals(sortedPokemons.size(), pokemonsFromPokedex.size());
        // Vérification de chaque Pokémon dans l'ordre alphabétique
        for (int i = 0; i < sortedPokemons.size(); i++) {
            assertEquals(sortedPokemons.get(i), pokemonsFromPokedex.get(i));
        }

        /** Test ordre alphabétique **/
        sortedPokemons.sort(Comparator.comparing(Pokemon::getName));

        // Appel à getPokemons avec un comparateur qui trie par ordre alphabétique
        pokemonsFromPokedex = pokedex.getPokemons(PokemonComparators.NAME);

        // Vérifications de la taille
        assertEquals(sortedPokemons.size(), pokemonsFromPokedex.size());
        // Vérification de chaque Pokémon dans l'ordre alphabétique
        for (int i = 0; i < sortedPokemons.size(); i++) {
            assertEquals(sortedPokemons.get(i), pokemonsFromPokedex.get(i));
        }
    }
}
