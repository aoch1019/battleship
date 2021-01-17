
import java.util.*;

public class BattleshipGame {

	private Ocean o;
	private ArrayList<String> leaderboard;
	
	public BattleshipGame() {
		o = new Ocean();
		o.placeAllShipsRandomly();
		leaderboard = new ArrayList<String>();
	}
	
	/**
	 * Asks the user for coordinates and handles accordingly until all ships are destroyed.
	 * Then adds the score to the leader board and asks if user if they'd like to play again.
	 */
	void play() {
		Ocean.revealBoard(o);
		Scanner s = new Scanner(System.in);
		while(!o.isGameOver()) {
			o.print();
			System.out.println("Select a row");
			int row = s.nextInt();
			System.out.println("Select a column");
			int column = s.nextInt();
			while(row < 0 || row > 9 || column < 0 || column > 9) {
				System.out.println("Please select a a valid spot...");
				System.out.println("Select a row");
				row = s.nextInt();
				System.out.println("Select a column");
				column = s.nextInt();
			}
			o.shootAt(row, column);
		}
		o.print();
		System.out.println("You won! Your score is " + o.getShotsFired());
		System.out.println("What's your name for our leaderboard?");
		String name = s.next();
		String entry = "" + o.getShotsFired() + " " + name;
		insertScore(entry);
		printLeaderboard();
		System.out.println("Would you like to play again? (Y/N)");
		String answer = s.next();
		if(answer.equals("Y") || answer.equals("y")){
			System.out.println("Playing again...");
			o = new Ocean();
			o.placeAllShipsRandomly();
			play();
		}
	}
	
	/**
	 * Inserts the user's score into the leader board
	 * @param entry The user's score and name combines as a string with a space separating them
	 */
	void insertScore(String entry) {
		
		int entryScore  = Integer.parseInt(entry.split(" ")[0]);
		for(int i = 0; i < leaderboard.size(); i++) {
			int currScore = Integer.parseInt(leaderboard.get(i).split(" ")[0]);
			if(entryScore <= currScore) {
				leaderboard.add(i, entry);
				return;
			}			
		}

		leaderboard.add(entry);
	}
	
	/**
	 * Iterates through the ordered leader board and displays the scores.
	 */
	void printLeaderboard() {
		for(int i = 0; i < leaderboard.size(); i++) {
			System.out.println((i + 1) + ". " + leaderboard.get(i));
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BattleshipGame bg = new BattleshipGame();
		bg.play();
	}

}
