package fr.univavignon.pokedex.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PokemonLoader {

    public List<Pokemon> loadPokemons(String filename) {
        List<Pokemon> pokemons = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename))))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int index = Integer.parseInt(parts[0]);
                String name = parts[1].replace("\"", "");
                int attack = Integer.parseInt(parts[2]);
                int defense = Integer.parseInt(parts[3]);
                int stamina = Integer.parseInt(parts[4]);
                int cp = Integer.parseInt(parts[5]);
                int hp = Integer.parseInt(parts[6]);
                int dust = Integer.parseInt(parts[7]);
                int candy = Integer.parseInt(parts[8]);
                int iv = Integer.parseInt(parts[9]);

                pokemons.add(new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv));
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return pokemons;
    }

    public List<PokemonMetadata> loadPokemonsMetadata(String filename) {
        List<PokemonMetadata> pokemons = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename))))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int index = Integer.parseInt(parts[0]);
                String name = parts[1].replace("\"", "");
                int attack = Integer.parseInt(parts[2]);
                int defense = Integer.parseInt(parts[3]);
                int stamina = Integer.parseInt(parts[4]);

                pokemons.add(new PokemonMetadata(index, name, attack, defense, stamina));

            }
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return pokemons;
    }
}

