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
    private Pokemon pokemon;

    @Before
    public void setUp() {
        pokedex = Mockito.mock(IPokedex.class);
        pokemon = new Pokemon(1, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
    }

    @Test
    public void testSize() {
        when(pokedex.size()).thenReturn(151);
        //Vérification de la taille
        assertEquals(151, pokedex.size());
    }
    @Test
    public void testAddPokemon() {
        when(pokedex.addPokemon(pokemon)).thenReturn(1);

        //Vérification du retour de la fonction
        assertEquals(1, pokedex.addPokemon(pokemon));
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        when(pokedex.getPokemon(0)).thenReturn(pokemon);
        Pokemon result = pokedex.getPokemon(0);

        //Vérification de la correspondance avec le pokemon voulu
        assertEquals(pokemon, result);
    }

    @Test
    public void testGetPokemons() {
        when(pokedex.getPokemons()).thenReturn(List.of(pokemon));
        List<Pokemon> pokemons = pokedex.getPokemons();

        //Vérification de la taille
        assertEquals(1, pokemons.size());

        //Vérification de la présence du pokemon
        assertEquals(pokemon, pokemons.get(0));
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