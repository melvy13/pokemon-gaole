import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
    // Initialising all Pokemons in the game
    FireType chimchar = new FireType("Chimchar", 81, 49, 38);
    FireType charmeleon = new FireType("Charmeleon", 100, 72, 53);
    FireType blaziken = new FireType("Blaziken", 137, 122, 73);
    WaterType piplup = new WaterType("Piplup", 88, 51, 45);
    WaterType poliwhirl = new WaterType("Poliwhirl", 104, 46, 58);
    WaterType blastoise = new WaterType("Blastoise", 133, 86, 101);
    WaterType greninja = new WaterType("Greninja", 158, 120, 86);
    GrassType rowlet = new GrassType("Rowlet", 90, 42, 35);
    GrassType bayleef = new GrassType("Bayleef", 104, 58, 74);
    GrassType torterra = new GrassType("Torterra", 155, 80, 100);
    ElectricType shinx = new ElectricType("Shinx", 76, 42, 33);
    ElectricType luxio = new ElectricType("Luxio", 107, 74, 69);
    ElectricType luxray = new ElectricType("Luxray", 136, 103, 74);
    GroundType sandshrew = new GroundType("Sandshrew", 70, 45, 58);
    GroundType nidorina = new GroundType("Nidorina", 118, 60, 65);
    GroundType sandslash = new GroundType("Sandslash", 143, 90, 100);
    Pokemon[] grade1 = {chimchar, piplup, rowlet, shinx, sandshrew};
    Pokemon[] grade2 = {charmeleon, poliwhirl, bayleef, luxio, nidorina};
    Pokemon[] grade3 = {blaziken, blastoise, torterra, luxray, sandslash};
    Pokemon[] grade4 = {greninja};

	PokeBall pokeball = new PokeBall("Poke Ball", 1);
	PokeBall greatball = new PokeBall("Great Ball", 1.5);
	PokeBall ultraball = new PokeBall("Ultra Ball", 2);
	PokeBall masterball = new PokeBall("Master Ball", 100);
	PokeBall[] ballList = {pokeball, greatball, ultraball, masterball};

    public static ArrayList<Pokemon> pool = new ArrayList<Pokemon>(); 
	
    Random rand = new Random();
    Scanner input = new Scanner(System.in);
    Player player = new Player();
    Battle battle = new Battle();
	Database DB = new Database();

	// Grade1 = Level 30 ; Grade2 = Level 40 ; Grade3 = Level 50 ; Grade4 = Level 60
	private void setPokemonLevel() {
		for (Pokemon i : grade1) {
			i.setLevel(30);
		}

		for (Pokemon i : grade2) {
			i.setLevel(40);
		}

		for (Pokemon i : grade3) {
			i.setLevel(50);
		}

		for (Pokemon i : grade4) {
			i.setLevel(60);
		}
	}
	
    // Display 3 random Pokemons from Grade 1-3 each, let user choose 1 and determine the Pokemon pool (The Pokemons that may appear in battle later)
    // If Grade1 Pokemon is chosen -> Pool = Grade1 + Grade2
    // If Grade2 Pokemon is chosen -> Pool = Grade1 + Grade2 + Grade3
    // If Grade3 Pokemon is chosen -> Pool = Grade2 + Grade3 + Grade4
    private void setPokemonPool() {
        int i1 = rand.nextInt(grade1.length);
        int i2 = rand.nextInt(grade2.length);
        int i3 = rand.nextInt(grade3.length);
        System.out.printf("[1] %s\n", grade1[i1].getName());
        System.out.printf("[2] %s\n", grade2[i2].getName());
        System.out.printf("[3] %s\n", grade3[i3].getName());
        System.out.print("Choose a Pokémon!: ");
        int choice = input.nextInt();

        if (choice == 1) {
            System.out.printf("You have chosen %s!\n", grade1[i1].getName());
            DB.addPokemon(grade1[i1]);
            for (Pokemon i : grade1) {
                pool.add(i);
            }

            for (Pokemon i : grade2) {
                pool.add(i);
            }
        }

        else if (choice == 2) {
            System.out.printf("You have chosen %s!\n", grade2[i2].getName());
            DB.addPokemon(grade2[i2]);
            for (Pokemon i : grade1) {
                pool.add(i);
            }

            for (Pokemon i : grade2) {
                pool.add(i);
            }

            for (Pokemon i : grade3) {
                pool.add(i);
            }
        }

        else if (choice == 3) {
            System.out.printf("You have chosen %s!\n", grade3[i3].getName());
            DB.addPokemon(grade3[i3]);
            for (Pokemon i : grade2) {
                pool.add(i);
            }

            for (Pokemon i : grade3) {
                pool.add(i);
            }

            for (Pokemon i : grade4) {
                pool.add(i);
            }
        }
    }

	// Generate a random Pokeball and throw at one of the wild pokemons to catch it
	private void catchPokemon() {
		double catchRate;
		Pokemon toAdd = null;
		int level = 30;
		int i = rand.nextInt(ballList.length);
		System.out.printf("A %s is generated!\n", ballList[i]);
		catchRate = ballList[i].getCatchRate();
		System.out.printf("[1] %s\n[2] %s\n", battle.getWild1(), battle.getWild2());
		System.out.println("Throw on which Pokemon?: ");
		int choice = input.nextInt();

		if (choice == 1) {
			toAdd = battle.getWild1();
			level = battle.getWild1().getLevel();
		} else if (choice == 2) {
			toAdd = battle.getWild2();
			level = battle.getWild2().getLevel();
		}

		switch (level) {
			case 30:
				catchRate *= 60; // Grade1: Pokeball 60%, GreatBall 90%, UltraBall 100%
				break;
			case 40:
				catchRate *= 45; // Grade2: Pokeball 45%, GreatBall 67.5%, UltraBall 90%
				break;
			case 50:
				catchRate *= 30; // Grade3: Pokeball 30%, GreatBall 45%, UltraBall 60%
				break;
			case 60:
				catchRate *= 20; // Grade4: Pokeball 20%, Greatball 30%, UltraBall 40%
				break;
		}

		int randomNo = rand.nextInt(1,101);
		System.out.println(randomNo);
		if (randomNo <= catchRate) {
			System.out.println("You have successfully caught the Pokemon!");
			DB.addPokemon(toAdd);
		}

		else {
			System.out.println("Oh no! The Pokemon escaped!");
		}
	}

	// Game flow
	public void startGame() {
		setPokemonLevel(); // Initialise Pokemon levels
		DB.loadDB(); // Load player database
		
		System.out.println("--------------------------------");
		System.out.println("   Welcome to Pokémon Ga-Olé!   ");
		System.out.println("    \"Battle and Catch\" mode    ");
		System.out.println("--------------------------------\n");

		System.out.print("Enter your User ID: ");
		int playerid = input.nextInt();
		DB.setPlayerID(playerid);

		System.out.println("\033[1mCatch Time!\033[0m");
		setPokemonPool();
		
		System.out.println("\n\033[1mDepart For Battle!\033[0m");
		battle.startBattle();

		System.out.println("\033[1mCatch Pokémon!\033[0m");
		catchPokemon();

		System.out.println("\033[1mCheck Your Results!\033[0m");
		// Calculate score

		System.out.println("\033[1mGa-Olé Medals\033[0m");
		// Gaole Medals
	}
}

