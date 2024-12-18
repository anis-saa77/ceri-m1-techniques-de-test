package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory{
    private PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy){
        PokemonMetadata metadata;
        try {
            metadata = metadataProvider.getPokemonMetadata(index);
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }
        int attack = metadata.getAttack();
        String name = metadata.getName();
        int defense = metadata.getDefense();
        int stamina = metadata.getStamina();
        return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, 0);
    }
}
