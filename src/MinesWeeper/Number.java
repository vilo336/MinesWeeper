package MinesWeeper;

public class Number extends Tile {
	private int number;

	public Number(int number) {
		this.number = number;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return String.valueOf(number);
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

}
