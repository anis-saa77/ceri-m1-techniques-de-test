package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IPokedexTest{
    private IPokedex pokedex;
    private PokemonLoader pokemonLoader = new PokemonLoader();
    private List<Pokemon> pokemons ;
    @Before
    public void setUp() {
        pokedex = Mockito.mock(IPokedex.class);
        pokemons = pokemonLoader.loadPokemons("pokedexfile");
    }

    @Test
    public void testSize() {
        when(pokedex.size()).thenReturn(0);
        assertEquals(0, pokedex.size());
        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);
            // Simuler la taille du pokedex après chaque ajout
            when(pokedex.size()).thenReturn(i + 1);

            // Ajouter le Pokémon et vérifier la taille
            pokedex.addPokemon(pokemon);
            assertEquals(i + 1, pokedex.size());
        }
    }
    @Test
    public void testAddPokemon() {
        for (Pokemon pokemon : pokemons) {
            when(pokedex.addPokemon(pokemon)).thenReturn(pokemon.getIndex());
            assertEquals(pokemon.getIndex(), pokedex.addPokemon(pokemon));
        }
    }
    @Test
    public void testGetPokemon() throws PokedexException {
        for (Pokemon pokemon : pokemons) {
            int index = pokemon.getIndex();
            when(pokedex.getPokemon(index)).thenReturn(pokemon);
            assertEquals(pokedex.getPokemon(index), pokemon);
        }
    }
    @Test
    public void testGetPokemonWithInvalidIndex() throws PokedexException {
        int invalidIndex1 = -1;
        int invalidIndex2 = 152;
        when(pokedex.getPokemon(invalidIndex1)).thenThrow(new PokedexException("Invalid index"));
        when(pokedex.getPokemon(invalidIndex2)).thenThrow(new PokedexException("Invalid index"));

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
        //TODO VERFIER TOUTE CETTE MERDE !!!!!!!!!
        List<Pokemon> exceptedPokemons = new ArrayList<>();

        when(pokedex.getPokemons()).thenReturn(exceptedPokemons);

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
        //TODO Test ordre par les index
        List<Pokemon> sortedPokemons = new ArrayList<>(pokemons); //pokemons est déjà trié selon les indices des pokemons
        // Mock du comportement du Pokedex pour renvoyer la liste triée
        when(pokedex.getPokemons(any(Comparator.class))).thenReturn(sortedPokemons);

        // Appel à getPokemons avec un comparateur qui trie par ordre alphabétique
        List<Pokemon> pokemonsFromPokedex = pokedex.getPokemons(Comparator.comparing(Pokemon::getIndex));

        // Vérifications de la taille
        assertEquals(sortedPokemons.size(), pokemonsFromPokedex.size());
        // Vérification de chaque Pokémon dans l'ordre alphabétique
        for (int i = 0; i < sortedPokemons.size(); i++) {
            assertEquals(sortedPokemons.get(i), pokemonsFromPokedex.get(i));
        }

        // TODO Test ordre alphabétique
        sortedPokemons.sort(Comparator.comparing(Pokemon::getName));

        // Mock du comportement du Pokedex pour renvoyer la liste triée
        when(pokedex.getPokemons(any(Comparator.class))).thenReturn(sortedPokemons);

        // Appel à getPokemons avec un comparateur qui trie par ordre alphabétique
        pokemonsFromPokedex = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));

        // Vérifications de la taille
        assertEquals(sortedPokemons.size(), pokemonsFromPokedex.size());
        // Vérification de chaque Pokémon dans l'ordre alphabétique
        for (int i = 0; i < sortedPokemons.size(); i++) {
            assertEquals(sortedPokemons.get(i), pokemonsFromPokedex.get(i));
        }
    }
}
