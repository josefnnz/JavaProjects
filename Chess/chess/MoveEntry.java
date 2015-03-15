/** MoveEntry is used to log moves in a Chess game.
  * It holds:
  * 1) The Piece that was moved.
  * 2) The (x,y) location of the square from which the Piece originated. 
  * 3) The (x,y) location of the square to which the Piece was moved.
  */

package chess;

public class MoveEntry {
	private final Piece p;
	private final int xInitial;
	private final int yInitial;
	private final int xTerminal;
	private final int yTerminal;

	public MoveEntry(Piece p, int xInitial, int yInitial, int xTerminal, int yTerminal) {
		this.p = p;
		this.xInitial = xInitial;
		this.yInitial = yInitial;
		this.xTerminal = xTerminal;
		this.yTerminal = yTerminal;
	}

	public Piece getPiece() {
		return p;
	}

	public int xInitial() {
		return xInitial;
	}

	public int yInitial() {
		return yInitial;
	}

	public int xTerminal() {
		return xTerminal;
	}

	public int yTerminal() {
		return yTerminal;
	}

}
