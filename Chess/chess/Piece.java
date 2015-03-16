package chess;

import java.util.HashSet;

public abstract class Piece {
	// Determine if piece is Black or White.
	public boolean isBlack;
	// Determine whether piece is King, Queen, Bishop, Knight, Rook, or Pawn.
	public String type;
	// Board that piece is located on.
	public Board board;
	// Current location of piece at (x,y).
	public int x;
	public int y;
	// Squares THIS piece is attacking.
	public HashSet<Square> attacking;

	/** Return true if THIS piece is Black.
	  * Return false if White. */
	public boolean isBlack() {
		return isBlack;
	}

	/** Returns type of piece. */
	public String getType() {
		return type;
	}

	/** Set x-coordinate of Square where Piece is located. */
	public void setX(int x) {
		this.x = x;
	}

	/** Set y-coordinate of Square where Piece is located. */
	public void setY(int y) {
		this.y = y;
	}

	/** Return x-coordinate of Square where Piece is located. */
	public int getX() {
		return x;
	}

	/** Return y-coordinate of Square where Piece is located. */
	public int getY() {
		return y;
	}

	/** Returns list of squares THIS Piece is attacking. 
	  * HashSet attacking created in constructor of each specific piece.
	  * Hence, attacking will not be null by the calling of getAttacking. */
	public HashSet<Square> getAttacking() {
		return attacking;
	}

	/** Creates HashSet containing the Squares that THIS piece is currently attacking.
	  * This HashSet consists of only in bounds Squares (i.e. Squares located on the board).
	  * The Squares in this HashSet are candidates for THIS piece to move to.
	  * The Squares THIS piece is attacking change whenever this piece moves to a new location.
	  * The HashSet of Squares will be used in the validMove method. */
	public abstract void findSquaresAttacking();

	/** Returns true if location (x,y) is valid Square for piece to move.
	  * Returns false otherwise. 
	  * Does not take into account whether THIS piece moving will result in king be in check.
	  * The board has a validMove that ensures a piece's movement does not 
	  * result in its king being in check.
	  * Assumes HashSet ATTACKING is up-to-date.
	  * NOTE: ATTACKING only holds squares that THIS piece can legally move to. */
	public boolean validMove(int x, int y) {
		this.findSquaresAttacking();

		if (!inBounds(x, y)) {
			// Evaluates if location (x,y) is not on the board.
			return false;
		}

		Square target = new Square(x, y);
		return attacking.contains(target);
	}

	/** Returns true if Square at location (x,y) is a valid location on the board.
	  * (The board is 8x8, so valid locations include 0 <= x <= 7 and 0 <= y <= 7.)
	  * Returns false otherwise. */
	public boolean inBounds(int x, int y) {
		return (x >= 0) && (x <= 7) && (y >= 0) && (y <= 7);
	}

	@Override
	public boolean equals(Object obj) {
		Square thisSquareLocation = new Square(this.x, this.y);
		Piece otherPiece = (Piece) obj;
		Square otherPieceLocation = new Square(otherPiece.getX(), otherPiece.getY());

		return thisSquareLocation.equals(otherPieceLocation);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.x;
		result = 31 * result + this.y;
		return result;
	}

}
