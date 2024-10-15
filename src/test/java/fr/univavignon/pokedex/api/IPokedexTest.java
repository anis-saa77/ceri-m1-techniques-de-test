package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IPokedexTest{
    private IPokedex pokedex;
    PokemonLoader pokemonLoader = new PokemonLoader();
    List<Pokemon> pokemons ;

    @Before
    public void setUp() {
        pokedex = Mockito.mock(IPokedex.class);
        pokemons = pokemonLoader.loadPokemons("pokedexfile");
    }

    @Test
    public void testSize() {
        Pokemon pokemon1 = pokemons.get(0);
        Pokemon pokemon2 = pokemons.get(1);

        // Simuler l'ajout de Pokémon
        when(pokedex.size()).thenReturn(1).thenReturn(2); // Simule l'augmentation de la taille

        // Ajouter un Pokémon et vérifier la taille
        pokedex.addPokemon(pokemon1);
        assertEquals(1, pokedex.size());

        // Ajouter un autre Pokémon et vérifier la taille
        pokedex.addPokemon(pokemon2);
        assertEquals(2, pokedex.size());
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

//        when(pokedex.getPokemons()).thenReturn(List.of(pokemon1));
//        List<Pokemon> pokemons = pokedex.getPokemons();
//
//        //Vérification de la taille
//        assertEquals(1, pokemons.size());
//
//        //Vérification de la présence du pokemon
//        assertEquals(pokemon1, pokemons.get(0));

        //TODO généraliser
        when(pokedex.getPokemons()).thenReturn(pokemons);
        List<Pokemon> pokemonsList = pokedex.getPokemons();

        //Vérification de la taille
        assertEquals(151, pokemons.size());

        //Vérification de la présence du pokemon
        assertEquals(pokemonsList.get(0), pokemons.get(0));
    }

    @Test
    public void testGetPokemonsWithOrder() {
        Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        List<Pokemon> sortedPokemons = new ArrayList<>();
        sortedPokemons.add(bulbizarre);
        sortedPokemons.add(aquali);
        // Mock du comportement du Pokedex pour renvoyer la liste non triée
        when(pokedex.getPokemons(any(Comparator.class))).thenReturn(sortedPokemons);

        // Tri des Pokémon en fonction de l'index dans le pokedex
        List<Pokemon> pokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getIndex));

        // Vérifications
        assertEquals(2, pokemons.size());
        assertEquals(bulbizarre, pokemons.get(0));
        assertEquals(aquali, pokemons.get(1));
    }
}