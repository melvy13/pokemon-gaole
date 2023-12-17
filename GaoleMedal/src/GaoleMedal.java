import java.util.Random;
import java.util.ArrayList;

public class GaoleMedal {
	//attributes
	private int gaoleMedals = 0;
    private int goldenChips = 0;
    private int goldenSands = 0;
    private Random random = new Random();
    private ArrayList<Pokemon> pokemonRanAwayList = new ArrayList<Pokemon>();
    
    // This method check & add to ArrayList if a defeated Pokemon ran away after battle
    public void checkPokemonAfterBattle(Pokemon pokemon) {
        if (pokemon.defeated() && pokemon.ranAway()) { // need to check how yana write battle
            pokemonRanAwayList.add(pokemon);
        }
    }
	
    // return golden chip, golden sand, and total gaole medal earned after each battle
	public void earnedGaoleMedal() {
		for (Pokemon pokemon : pokemonRanAwayList) {
			int grade = pokemon.getGrade(); //get Pokemon attribute - grade
			if (grade == 1 || grade == 2) { // grade 1 & 2
                	goldenSands++;
                	gaoleMedals += 10;
            } else if (grade == 3 || grade == 4) { // grade 3 & 4
            	goldenChips++;
            	gaoleMedals += 30;
            }
		}
	    // Print summary
	    System.out.println("Congratulations! You've obtained:");
	    System.out.println(goldenChips + " golden chip(s) and " + goldenSands + " golden sand(s)");
	    System.out.println();
	    
	    // Reset goldenChips and goldenSands
        goldenChips = 0;
        goldenSands = 0;
	}
	
	// check if valid to get Miracle Item after each round of battle
	public MiracleItem checkMiracleItem() {
		if (gaoleMedals >= 160) {
			gaoleMedals -= 160; // Deduct the medals for the item
            return MiracleItemChance();
        }
        return null;
    }
	
	// Miracle Item Chance!
	private MiracleItem MiracleItemChance() {
        switch (random.nextInt(3)) {
            case 0:
                return new AttackCapsule();
            case 1:
                return new DefenseCapsule();
            default:
                return new PokeBallPower();
        }
    }
}
	

