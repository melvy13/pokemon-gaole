import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
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
    ArrayList<Pokemon> pool = new ArrayList<Pokemon>();
    Random rand = new Random();
    Scanner input = new Scanner(System.in);

    public void setPokemonPool() {
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
            // add grade1[i1] into player disklist
            for (Pokemon i : grade1) {
                pool.add(i);
            }

            for (Pokemon i : grade2) {
                pool.add(i);
            }
        }

        else if (choice == 2) {
            System.out.printf("You have chosen %s!\n", grade2[i2].getName());
            // add grade2[i2] into player disklist
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
            // add grade3[i3] into player disklist
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

    public void generateWildPokemons() {
		int wild1 = rand.nextInt(pool.size());
        int wild2 = rand.nextInt(pool.size());
        System.out.printf("A wild %s and %s have appeared!\n", pool.get(wild1).getName(), pool.get(wild2).getName());
    }

    public void startGame() {
	System.out.println("--------------------------------");
	System.out.println("   Welcome to Pokémon Ga-Olé!   ");
	System.out.println("    \"Battle and Catch\" mode    ");
	System.out.println("--------------------------------\n");
	
	System.out.println("\033[1mCatch Time!\033[0m");
	setPokemonPool();
	
	System.out.println("\n\033[1mDepart For Battle!\033[0m");
	generateWildPokemons();
    }
}

