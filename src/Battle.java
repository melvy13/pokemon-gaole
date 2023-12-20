import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Battle {
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon wild1;
    private Pokemon wild2;
    private Player player;
    Random rand = new Random();
    Scanner input = new Scanner(System.in);
    private ArrayList<Pokemon> pool = Game.pool;

    // Battle flow
    public void startBattle() {
        displayWildPokemons();
        choosePokemons();
        battleSequence();
    }

    // Display 2 random wild Pokemons from pool
    private void displayWildPokemons() {
        int i1 = rand.nextInt(pool.size());
		int i2;
        do {
			i2 = rand.nextInt(pool.size());
		} while (i1 == i2); // to prevent the possibility of getting the same wild Pokemon as the first one
        System.out.printf("A wild %s and %s have appeared!\n", pool.get(i1).getName(), pool.get(i2).getName());
        wild1 = pool.get(i1);
        wild2 = pool.get(i2);
    }

    // Choose 2 Pokemons from player Pokemons list (or rent a Pokemon)
    private void choosePokemons() {
        ArrayList<Pokemon> playerPokemons = new ArrayList<>();
        // Get player Pokemons list - Store in ArrayList
        System.out.println("You currently have these Pokemons!: ");
        for (int i = 0; i < playerPokemons.size(); i++) {
            System.out.printf("[%d] %s\n", i+1, playerPokemons.get(i));
        }

        // If playerPokemons has only 1 Pokemon - Rent a random one from the pool
        if (playerPokemons.size() < 2) {
            System.out.println("You don't have enough Pokemons! You need to rent a Pokemon!");
            int rent = rand.nextInt(pool.size());
            System.out.printf("You have rented a %s!\n", pool.get(rent).getName());
			System.out.printf("You sent out %s & %s!\n", playerPokemons.get(0).getName(), pool.get(rent).getName());
            pokemon1 = playerPokemons.get(0);
            pokemon2 = pool.get(rent);
        }

        // Else choose 2 Pokemons from playerPokemons to send out
        else {
            System.out.print("Choose your first Pokemon!: ");
			int choice1 = input.nextInt();
			System.out.print("Choose your second Pokemon!: ");
			int choice2 = input.nextInt();
            System.out.printf("You sent out %s & %s!\n", playerPokemons.get(choice1-1).getName(), playerPokemons.get(choice2-1).getName());
            pokemon1 = playerPokemons.get(choice1-1);
            pokemon2 = playerPokemons.get(choice2-1);
        }
    }
}
