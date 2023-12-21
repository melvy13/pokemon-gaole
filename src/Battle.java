import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Battle {
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon wild1;
    private Pokemon wild2;
    Random rand = new Random();
    Scanner input = new Scanner(System.in);
	
    private ArrayList<Pokemon> pool = Game.pool;

	public Pokemon getWild1() {
        return wild1;
    }

    public Pokemon getWild2() {
        return wild2;
    }
	
    // Battle flow
    public void startBattle(ArrayList<Pokemon> playerPokemons) {
        displayWildPokemons();
        choosePokemons(playerPokemons);
        while (true) {
            String attacker = battleSequence();
            dealDamage(attacker);

            if (wild1.getHp() == 0 && wild2.getHp() == 0) {
                System.out.println("You win!");
                break;
            } 
            
            else if (pokemon1.getHp() == 0 && pokemon2.getHp() == 0) {
                System.out.println("You lose!");
                break;
            }
        }
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
    private void choosePokemons(ArrayList<Pokemon> playerPokemons) {
        System.out.println("You currently have these Pokemons!: ");
        for (int i = 0; i < playerPokemons.size(); i++) {
            System.out.printf("[%d] %s\n", i+1, playerPokemons.get(i));
        }

        // If playerPokemons has only 1 Pokemon - Rent a random one from the pool
        if (playerPokemons.size() < 2) {
            System.out.println("You don't have enough Pokemons! You need to rent a Pokemon!");
            int rent = rand.nextInt(pool.size());
            System.out.printf("You have randomly rented a %s!\n", pool.get(rent).getName());
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

	// Determine who attacks first
    // 1. Computer generates a hidden number from 1-100
    // 2. Computer generates another random number to represent enemy's input
    // 3. User input a random number
    // 4. Whoever's random number is closer to the hidden number will attack first
    private String battleSequence() {
		System.out.println("\nA hidden number from 1-100 is generated. Whoever's input number is closer to the hidden number will attack!");
        int hiddenNo = rand.nextInt(1, 101);
        System.out.print("Input a random number from 1-100!: ");
        int userNo = input.nextInt();
        String attacker; // to return either "enemy" or "user" for whoever is attacking

        while (true) {
            int enemyNo = rand.nextInt(1, 101);
            int enemyDiff = Math.abs(hiddenNo - enemyNo);
            int userDiff = Math.abs(hiddenNo - userNo);

            if (enemyDiff == userDiff) {
                continue; // reloop & generate a new enemy's number if userDiff & enemyDiff are the same
            }

            System.out.printf("The hidden number was %d!\n", hiddenNo);
            System.out.printf("Your number: %d  Enemy's number: %d\n", userNo, enemyNo);

            if (enemyDiff < userDiff) {
                System.out.println("Enemy's number is closer to the hidden number!");
                System.out.println("Enemy will attack!");
                attacking = "enemy";
                break;
            }

            else {
                System.out.println("Your number is closer to the hidden number!");
                System.out.println("You will attack!");
                attacking = "user";
                break;
            }
        }
        return attacker;
    }

	// Calculate damage
	private int calculateDamage(Pokemon attacker, Pokemon defender) {
        int att = attacker.getAtt();
        int def = defender.getDef();
        int level = attacker.getLevel();
        double effectiveness = defender.effectiveness(attacker.getType());       

        double damage = ((0.4 * level + 2) * att / def + 2) * effectiveness * rand.nextDouble(0.85, 1.00);
        return (int)damage;
    }

	// Deal damage
    private void dealDamage(String attacker) {
        if (attacker == "user") {
            System.out.printf("%s: %d HP ; %s: %d HP\n", wild1.getName(), wild1.getHp(), wild2.getName(), wild2.getHp());
            System.out.printf("Your %s & %s attacked!\n", pokemon1.getName(), pokemon2.getName());
            wild1.takeDamage(calculateDamage(pokemon1, wild1));
            wild2.takeDamage(calculateDamage(pokemon1, wild2));
            wild1.takeDamage(calculateDamage(pokemon2, wild1));
            wild2.takeDamage(calculateDamage(pokemon2, wild2));
            convertNegativeHp(wild1);
            convertNegativeHp(wild2);
            System.out.printf("The wild %s & %s took damage!\n", wild1.getName(), wild2.getName());
            System.out.printf("%s: %d HP ; %s: %d HP\n", wild1.getName(), wild1.getHp(), wild2.getName(), wild2.getHp());
        }

        else if (attacker == "enemy") {
            System.out.printf("%s: %d HP ; %s: %d HP\n", pokemon1.getName(), pokemon1.getHp(), pokemon2.getName(), pokemon2.getHp());
            System.out.printf("The wild %s & %s attacked!\n", wild1.getName(), wild2.getName());
            pokemon1.takeDamage(calculateDamage(wild1, pokemon1));
            pokemon2.takeDamage(calculateDamage(wild1, pokemon2));
            pokemon1.takeDamage(calculateDamage(wild2, pokemon1));
            pokemon2.takeDamage(calculateDamage(wild2, pokemon2));
            convertNegativeHp(pokemon1);
            convertNegativeHp(pokemon2);
            System.out.printf("Your %s & %s took damage!\n", pokemon1.getName(), pokemon2.getName());
            System.out.printf("%s: %d HP ; %s: %d HP\n", pokemon1.getName(), pokemon1.getHp(), pokemon2.getName(), pokemon2.getHp());
        }
    }

    // Convert HP to 0 if Pokemon HP reaches below 0
    private void convertNegativeHp(Pokemon p) {
        if (p.getHp() < 0) {
            p.setHp(0);
        }
    }
}
