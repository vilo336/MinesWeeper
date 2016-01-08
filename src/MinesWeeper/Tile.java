package MinesWeeper;

public abstract class Tile {

	State state;
	int number = 0;

	public Tile() {

	}

	public Tile(State state, int number) {
		super();
		this.state = state;
		this.number = number;
	}

	public Tile(int number) {
		super();
		this.number = number;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

}
