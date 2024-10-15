package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory;
    private IPokedexFactory pokedexFactory;
    private IPokedex pokedex;

    @Before
    public void setUp() throws Exception {
        pokedexFactory = mock(IPokedexFactory.class);
        pokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
        pokedex = mock(IPokedex.class);
    }

    @Test
    public void testCreateTrainer() {
        // Création d'un dresseur
        String trainerName = "Anis";
        Team team = Team.VALOR;
        PokemonTrainer pokemonTrainer = new PokemonTrainer(trainerName, team, pokedex);

        // Gestion du comportement du mock
        when(pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(pokemonTrainer);
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactory);

        // Vérifications sur le résultat
        assertNotNull(trainer);
        assertEquals(trainerName, trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertNotNull(trainer.getPokedex());
    }
}

