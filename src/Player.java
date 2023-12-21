// to be updated

import java.util.ArrayList;

public class Player {
    private ArrayList<Pokemon> pokemonsOwned = new ArrayList<Pokemon>();

    public Player() {
    }

    public Player(ArrayList<Pokemon> pokemonsOwned) {
        this.pokemonsOwned = pokemonsOwned;
    }

    public ArrayList<Pokemon> getPokemonsOwned() {
        return pokemonsOwned;
    }

    public void setPokemonsOwned(ArrayList<Pokemon> pokemonsOwned) {
        this.pokemonsOwned = pokemonsOwned;
    }
}
