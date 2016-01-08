package MinesWeeper;

import java.util.Random;

public class Area {

	public Tile[][] tile;
	public int rowCount;
	public int columnCount;
	public int minesCount;
	public boolean gameOver = false;

	public Area(int rowCount, int columnCount, int mineCount) {

		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.minesCount = mineCount;
	}

	public Tile[][] createArea() {
		Tile[][] pole = new Tile[rowCount][columnCount];

		for (int i = 0; i < rowCount; i++) {
			// System.out.println();
			for (int j = 0; j < columnCount; j++) {
				pole[i][j] = new Number(0);
				pole[i][j].setState(State.CLOSED);
			}
		}
		return pole;

	}

	public Tile[][] setMines(Tile[][] pole) {

		Random random = new Random();
		int mines = minesCount;
		int positionOfRow;
		int positionOfColumn;
		while (mines > 0) {
			positionOfRow = random.nextInt(rowCount);
			positionOfColumn = random.nextInt(columnCount);
			if (!(pole[positionOfRow][positionOfColumn] instanceof Mine)) {
				pole[positionOfRow][positionOfColumn] = new Mine();
				pole[positionOfRow][positionOfColumn].setState(State.CLOSED);
				mines--;
			}
		}

		return pole;
	}

	public Tile[][] setNumbers(Tile[][] pole) {
		for (int i = 0; i < rowCount; i++) {

			for (int j = 0; j < columnCount; j++) {
				if (pole[i][j] instanceof Mine) {
					{
						pole = controlAndSet(pole, i, j);
					}
				}
			}
		}
		System.out.println();
		System.out.println();
		draw(pole);
		return pole;
	}

	private Tile[][] controlAndSet(Tile[][] tile, int row, int column) {
		if ((row - 1 > -1) && (column - 1 > -1) && (row - 1 < 9) && (column - 1 < 9)
				&& (tile[row - 1][column - 1] instanceof Number))
			tile[row - 1][column - 1].setNumber((((Number) tile[row - 1][column - 1]).getNumber()) + 1);

		if ((row - 1 > -1) && (column > -1) && (row - 1 < 9) && (column < 9)
				&& (tile[row - 1][column] instanceof Number))
			tile[row - 1][column].setNumber((((Number) tile[row - 1][column]).getNumber()) + 1);

		if ((row - 1 > -1) && (column + 1 > -1) && (row - 1 < 9) && (column + 1 < 9)
				&& (tile[row - 1][column + 1] instanceof Number))
			tile[row - 1][column + 1].setNumber((((Number) tile[row - 1][column + 1]).getNumber()) + 1);

		if ((row > -1) && (column + 1 > -1) && (row < 9) && (column + 1 < 9)
				&& (tile[row][column + 1] instanceof Number))
			tile[row][column + 1].setNumber((((Number) tile[row][column + 1]).getNumber()) + 1);

		if ((row + 1 > -1) && (column + 1 > -1) && (row + 1 < 9) && (column + 1 < 9)
				&& (tile[row + 1][column + 1] instanceof Number))
			tile[row + 1][column + 1].setNumber((((Number) tile[row + 1][column + 1]).getNumber()) + 1);

		if ((row + 1 > -1) && (column > -1) && (row + 1 < 9) && (column < 9)
				&& (tile[row + 1][column] instanceof Number))
			tile[row + 1][column].setNumber((((Number) tile[row + 1][column]).getNumber()) + 1);

		if ((row + 1 > -1) && (column - 1 > -1) && (row + 1 < 9) && (column - 1 < 9)
				&& (tile[row + 1][column - 1] instanceof Number))
			tile[row + 1][column - 1].setNumber((((Number) tile[row + 1][column - 1]).getNumber()) + 1);

		if ((row > -1) && (column - 1 > -1) && (row < 9) && (column - 1 < 9)
				&& (tile[row][column - 1] instanceof Number))
			tile[row][column - 1].setNumber((((Number) tile[row][column - 1]).getNumber()) + 1);

		return tile;

	}

	public boolean isEverythingUncover(Tile[][] pole) {
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (pole[i][j].getState() == State.CLOSED)
					return false;
			}
		}

		return true;
	}

	public Tile[][] uncover(String enter, Tile[][] pole) {
		char[] oneLetter = enter.toCharArray();
		System.out.println();
		int rowLetter = Character.getNumericValue(oneLetter[2]) - 10;
		int columnLetter = Character.getNumericValue(oneLetter[1]);
		if (oneLetter[0] == 'M') {
			pole[rowLetter][columnLetter].setState(State.MARKED);

		}

		if ((pole[rowLetter][columnLetter] instanceof Mine)
				&& pole[rowLetter][columnLetter].getState() != State.MARKED) {
			gameOver = true;
			System.out.println("\nKoniec hry !!");
		}
		if (oneLetter[0] == 'O') {

			pole[rowLetter][columnLetter].setState(State.OPEN);
			controlAndUncover(pole, rowLetter, columnLetter);
		}

		return pole;
	}

	public void draw(Tile[][] pole) {
		System.out.print("   ");
		int numberAscii = 65;
		for (int i = 0; i < columnCount; i++) {
			System.out.print(" " + i);
		}
		System.out.println("");
		for (int i = 0; i < rowCount; i++) {
			System.out.println();
			System.out.print(Character.toChars(numberAscii++));
			System.out.print("  ");

			for (int j = 0; j < columnCount; j++) {

				Tile tile = pole[i][j];
				if (tile.getState() == State.OPEN) {
					if (tile instanceof Number) {
						System.out.print(((Number) tile).getNumber() + " ");
					} else {
						System.out.print("X ");
					}
				} else if (tile.getState() == State.MARKED) {
					System.out.print("F ");
				} else {
					System.out.print("[]");
				}
			}
		}
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver
	 *            the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	private Tile[][] controlAndUncover(Tile[][] tile, int row, int column) {
		if ((row - 1 > -1) && (column - 1 > -1) && (row - 1 < 9) && (column - 1 < 9)
				&& (tile[row - 1][column - 1] instanceof Number)) {
			tile[row - 1][column - 1].setState(State.OPEN);
			if (tile[row - 1][column - 1].getNumber() == 0 && tile[row - 1][column - 1].getState() == State.CLOSED
					&& (row - 1 > -1) && (column - 1 > -1) && (row - 1 < 9) && (column - 1 < 9))

				controlAndUncover(tile, row - 1, column - 1);

		}
		if ((row - 1 > -1) && (column > -1) && (row - 1 < 9) && (column < 9)
				&& (tile[row - 1][column] instanceof Number)) {
			tile[row - 1][column].setState(State.OPEN);
			// if (tile[row - 1][column].getNumber() == 0 && tile[row +
			// 2][column + 2].getState() == State.CLOSED)
			// controlAndUncover(tile, row - 1, column);
		}
		if ((row - 1 > -1) && (column + 1 > -1) && (row - 1 < 9) && (column + 1 < 9)
				&& (tile[row - 1][column + 1] instanceof Number)) {
			tile[row - 1][column + 1].setState(State.OPEN);
			// if (tile[row - 1][column + 1].getNumber() == 0 && tile[row +
			// 2][column - 2].getState() == State.CLOSED)
			// controlAndUncover(tile, row - 1, column + 1);
		}
		if ((row > -1) && (column + 1 > -1) && (row < 9) && (column + 1 < 9)
				&& (tile[row][column + 1] instanceof Number)) {
			tile[row][column + 1].setState(State.OPEN);
			// if (tile[row][column + 1].getNumber() == 0 && tile[row -
			// 2][column + 2].getState() == State.CLOSED)
			// controlAndUncover(tile, row, column + 1);
		}
		if ((row + 1 > -1) && (column + 1 > -1) && (row + 1 < 9) && (column + 1 < 9)
				&& (tile[row + 1][column + 1] instanceof Number)) {
			tile[row + 1][column + 1].setState(State.OPEN);
			// if (tile[row + 1][column + 1].getNumber() == 0 && tile[row +
			// 2][column + 1].getState() == State.CLOSED)
			// controlAndUncover(tile, row + 1, column + 1);
		}
		if ((row + 1 > -1) && (column > -1) && (row + 1 < 9) && (column < 9)
				&& (tile[row + 1][column] instanceof Number)) {
			tile[row + 1][column].setState(State.OPEN);
			// if (tile[row + 1][column].getNumber() == 0 && tile[row +
			// 2][column+1].getState() == State.CLOSED)
			// controlAndUncover(tile, row + 1, column);
		}
		if ((row + 1 > -1) && (column - 1 > -1) && (row + 1 < 9) && (column - 1 < 9)
				&& (tile[row + 1][column - 1] instanceof Number)) {
			tile[row + 1][column - 1].setState(State.OPEN);
			// if (tile[row + 1][column - 1].getNumber() == 0 && tile[row +
			// 2][column - 2].getState() == State.CLOSED)
			// controlAndUncover(tile, row + 1, column - 1);
		}
		if ((row > -1) && (column - 1 > -1) && (row < 9) && (column - 1 < 9)
				&& (tile[row][column - 1] instanceof Number)) {
			tile[row][column - 1].setState(State.OPEN);
			// if (tile[row][column - 1].getNumber() == 0 && tile[row+1][column
			// - 2].getState() == State.CLOSED)
			// controlAndUncover(tile, row, column - 1);
		}
		return tile;

	}
}
