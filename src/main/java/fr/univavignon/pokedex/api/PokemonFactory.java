package fr.univavignon.pokedex.api;

/**
 * Factory for creating instances of {@link Pokemon}.
 * This class implements the {@link IPokemonFactory} interface and provides
 * a concrete implementation for creating Pokemon instances with the required properties.
 */
public class PokemonFactory implements IPokemonFactory {

    /**
     * Instance of {@link PokemonMetadataProvider} used to retrieve metadata about Pokemon.
     */
    private PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();

    /**
     * Creates a new {@link Pokemon} instance with the specified parameters.
     *
     * @param index The index of the Pokemon in the Pokedex (e.g., 1 for Bulbasaur).
     * @param cp    The combat power of the Pokemon.
     * @param hp    The hit points of the Pokemon.
     * @param dust  The amount of stardust required to power up the Pokemon.
     * @param candy The amount of candy required to power up the Pokemon.
     * @return A new instance of {@link Pokemon} with the specified parameters.
     * @throws RuntimeException if the metadata for the specified index cannot be retrieved.
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        PokemonMetadata metadata;
        try {
            metadata = metadataProvider.getPokemonMetadata(index);
        } catch (PokedexException e) {
            throw new RuntimeException("Failed to retrieve Pokemon metadata for index: " + index, e);
        }

        int attack = metadata.getAttack();
        String name = metadata.getName();
        int defense = metadata.getDefense();
        int stamina = metadata.getStamina();

        return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, 0);
    }
}
