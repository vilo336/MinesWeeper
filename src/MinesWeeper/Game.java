package MinesWeeper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {

	public static void main(String[] args) {

		Area board = new Area(9, 9, 9);
		Tile[][] pole = new Tile[9][9];
		long startTime = System.currentTimeMillis();
		// board.createArea();
		pole = board.setMines(board.createArea());
		board.setNumbers(pole);
		Scanner sc = new Scanner(System.in);
		String option = "";
		while (!board.isGameOver()) {
			System.out.println("\nZadaj suradnice v tvare M5B alebo O5B");
			option = sc.nextLine();
			switch (option) {
			case "X": {
				board.gameOver = true;
				break;
			}
			}
			if (board.isEverythingUncover(pole))
				board.gameOver = true;
			if (Pattern.matches("[M|O][0-8][A-I]", option))
				board.uncover(option, pole);
			board.draw(pole);
		}
		System.out.println("\nGoodbye");
		long endtime = System.currentTimeMillis();
		System.out.println("User: " + System.getProperty("user.name"));
		System.out.println("Duration: " + (new SimpleDateFormat("mm:ss:SSS")).format(new Date(endtime - startTime)));
		sc.close();
	}

}
