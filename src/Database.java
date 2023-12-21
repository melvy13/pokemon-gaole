import java.util.*;
import java.io.*;

public class Database {
	private Scanner pinput;
	private Formatter poutput;
	private ArrayList<PlayerRecord> player = new ArrayList<PlayerRecord>();
	
	// load & store all data from & to player file and close the file
	public void loadDB() {
		pinput = openInputFile("player.txt");
		readPlayerFile();
		closeInputFile(pinput);
	}
	public void storeDB() {
		poutput = openOutputFile("player.txt");
		writePlayerFile();
		closeOutputFile(poutput);
	}
	
	// open file for reading & writing
	public Scanner openInputFile(String filename) {
		Scanner initialinput = null;
		
		try {
			initialinput = new Scanner(new File(filename));
		}
		catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening player records.");
			System.exit(1);
		}
		return initialinput;
	}
	public Formatter openOutputFile(String filename) {
		Formatter initialoutput = null;
		
		try {
			initialoutput = new Formatter(new File(filename));
		}
		catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening player records.");
			System.exit(1);
		}
		return initialoutput;
	}
	
	public void readPlayerFile() {
		try {
			while (pinput.hasNext()) {
				PlayerRecord ply = new PlayerRecord();
				ply.setPlayerID(pinput.nextInt());
				ply.setPlayerName(pinput.next());
				ply.setPlayerScores(pinput.nextInt());
				ply.setPlayerGaoleMedal(pinput.nextInt());

				player.add(ply);
			}
		}
		catch (NoSuchElementException elementException) {
			System.err.println("Player file improperly formed.");
			pinput.close();
			System.exit(1);
		}
		catch (IllegalStateException stateException) {
			System.err.println("Error reading from player file.");
			System.exit(1);
		}
	}
	public void writePlayerFile() {
		for (int p = 0; p < player.size(); p++) {
			poutput.format("%d %s %d %d\n", player.get(p).getPlayerID(), 
					player.get(p).getPlayerName(), player.get(p).getPlayerScores(), 
					player.get(p).getPlayerGaoleMedal());
		}
	}
	
	public void closeInputFile(Scanner input) {
		if (input!=null)
			input.close();
	}
	public void closeOutputFile(Formatter output) {
		if (output!=null)
			output.close();
	}


	public void addScores(int playerID, int scoresToAdd) {
		
		// Find the player with the given ID
        PlayerRecord playerToUpdate = null;

        for (PlayerRecord playerRecord : player) {
            if (playerRecord.getPlayerID() == playerID) {
                playerToUpdate = playerRecord;
                break;
            }
        }

        // If the player is found, update the scores
        if (playerToUpdate != null) {
            int currentScores = playerToUpdate.getPlayerScores();
            int newScores = currentScores + scoresToAdd;

            // Update the player's scores in the PlayerRecord object
            playerToUpdate.setPlayerScores(newScores);

            // Update the player's record in the ArrayList
            player.set(player.indexOf(playerToUpdate), playerToUpdate);

            System.out.println("Scores added successfully. New scores: " + newScores);
        } else {
            System.out.println("Player not found!"); // If player is not found
        }
    }
		
	
	public void displayTop5scores() {
		// Sort the players based on scores in descending order
        Collections.sort(player, (player1, player2) -> Integer.compare(player2.getPlayerScores(), player1.getPlayerScores()));

        // Display the top 5 players
        System.out.println("Top 5 Players:");
        for (int i = 0; i < Math.min(5, player.size()); i++) {
            PlayerRecord currentPlayer = player.get(i);
            System.out.println("Player ID: " + currentPlayer.getPlayerID() + ", Scores: " + currentPlayer.getPlayerScores());
        }
	}
	
	public void displayPlayerGMedal(int targetPlayerID) {
		boolean foundPlayer = false;
		for (PlayerRecord currentPlayer : player) {
	        if (currentPlayer.getPlayerID() == targetPlayerID) {
	            System.out.println("Player ID: " + currentPlayer.getPlayerID() + ", Gaole Medals: " + currentPlayer.getPlayerGaoleMedal());
	            foundPlayer = true;
	            break;  // No need to continue looping if the player is found
	        }
	    }

	    if (!foundPlayer) {
	        System.out.println("Player with ID " + targetPlayerID + " not found.");
	    }
    }
	
	public void addGaoleMedals(int targetPlayerID, int medalsToAdd) {
        // Find the player with the given ID
        PlayerRecord playerToUpdate = null;
        for (PlayerRecord playerRecord : player) {
            if (playerRecord.getPlayerID() == targetPlayerID) {
                playerToUpdate = playerRecord;
                break;
            }
        }

        // If the player is found, update the Gaole Medals
        if (playerToUpdate != null) {
            int currentMedals = playerToUpdate.getPlayerGaoleMedal();
            int newMedals = currentMedals + medalsToAdd;

            // Update the player's Gaole Medals in the PlayerRecord object
            playerToUpdate.setPlayerGaoleMedal(newMedals);

            // Update the player's record in the ArrayList
            player.set(player.indexOf(playerToUpdate), playerToUpdate);

            System.out.println("Gaole Medals added successfully. New Gaole Medals: " + newMedals);
        } else {
            System.out.println("Player not found!"); // If player is not found
        }
    }
	
}
