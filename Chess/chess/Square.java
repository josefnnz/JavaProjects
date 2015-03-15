package chess;

public class Square {
	// Square located at (x,y) position on board.
	private final int x;
	private final  int y;

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** Return x-coordinate. */
	public int getX() {
		return x;
	}

	/** Return y-coordinate. */
	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		}

		final Square other = (Square) obj;
		if (this.x != other.x) {
			return false;
		} else if (this.y != other.y) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + x;
		result = 31 * result + y;
		return result;
	}

}
