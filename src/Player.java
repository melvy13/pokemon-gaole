// to be updated

import java.util.ArrayList;

public class Player {
    private ArrayList<Pokemon> pokemonCaught = new ArrayList<Pokemon>();

    public Player() {
    }

    public Player(ArrayList<Pokemon> pokemonCaught) {
        this.pokemonCaught = pokemonCaught;
    }

    public ArrayList<Pokemon> getPokemonCaught() {
        return pokemonCaught;
    }

    public void setPokemonCaught(ArrayList<Pokemon> pokemonCaught) {
        this.pokemonCaught = pokemonCaught;
    }
}
