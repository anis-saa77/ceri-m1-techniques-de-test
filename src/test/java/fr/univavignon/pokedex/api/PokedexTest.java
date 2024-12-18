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
    private List<PokemonMetadata> pokemonsMetadata;

    @Before
    public void setUp() {
        pokemons = pokemonLoader.loadPokemons("pokedexfile");
        pokemonsMetadata = pokemonLoader.loadPokemonsMetadata("pokedexfile");
        pokemonFactory = new PokemonFactory();
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
    @Test
    public void testCreatePokemon() {
        // Données d'entrée
        int index = 1;
        int cp = 500;
        int hp = 100;
        int dust = 50;
        int candy = 10;

        // Création du Pokémon
        Pokemon pokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérifications des propriétés
        assertNotNull("The created Pokemon should not be null", pokemon);
        assertEquals("Index should match the input index", index, pokemon.getIndex());
        assertEquals("CP should match the input CP", cp, pokemon.getCp());
        assertEquals("HP should match the input HP", hp, pokemon.getHp());
        assertEquals("Dust should match the input dust", dust, pokemon.getDust());
        assertEquals("Candy should match the input candy", candy, pokemon.getCandy());
        assertEquals("IV should initially be zero", 0, pokemon.getIv(), 0);

        // Vérifications des métadonnées via le metadataProvider
        assertEquals("Name should match metadata", "Bulbizarre", pokemon.getName());
        assertEquals("Attack should match metadata", 126, pokemon.getAttack());
        assertEquals("Defense should match metadata", 126, pokemon.getDefense());
        assertEquals("Stamina should match metadata", 90, pokemon.getStamina());
    }

    @Test(expected = RuntimeException.class)
    public void testCreatePokemonWithInvalidIndex() {
        // Test avec un index invalide
        int invalidIndex = 999;
        pokemonFactory.createPokemon(invalidIndex, 500, 100, 50, 10);
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        int index = 1;
        for (PokemonMetadata expectedMetadata : pokemonsMetadata) {
            // Appel de la méthode à tester
            PokemonMetadata actualMetadata = pokemonMetadataProvider.getPokemonMetadata(index);

            // Vérification des résultats
            assertNotNull(actualMetadata);
            assertEquals(expectedMetadata.getIndex(), actualMetadata.getIndex());
            assertEquals(expectedMetadata.getName(), actualMetadata.getName());
            assertEquals(expectedMetadata.getAttack(), actualMetadata.getAttack());
            assertEquals(expectedMetadata.getDefense(), actualMetadata.getDefense());
            assertEquals(expectedMetadata.getStamina(), actualMetadata.getStamina());

            index++ ;
        }
    }
    @Test
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        int invalidIndex = -1;
        // Vérification que l'exception est bien levée
        try {
            pokemonMetadataProvider.getPokemonMetadata(-1);
            fail("Expected a PokedexException to be thrown");
        } catch (PokedexException e) {
            assertEquals("Invalid index", e.getMessage());
        }
    }
}
