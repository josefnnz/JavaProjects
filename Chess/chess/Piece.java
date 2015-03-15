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
	  * Assumes HashSet ATTACKING is up-to-date. */
	public boolean validMove(int x, int y) {
		if (!inBounds(x, y)) {
			// Evaluates if location (x,y) is not on the board.
			return false;
		}

		Square target = new Square(x, y);
		if (!attacking.contains(target)) {
			// Evalutes if TARGET is not a Square THIS piece is attacking.
			return false;
		}

		Piece targetOccupant = board.getPiece(x, y);
		if ((targetOccupant != null) && (targetOccupant.isBlack() == this.isBlack())) {
			// Evaluates if a piece already lies on TARGET and the TARGET piece color
			// is the same as THIS piece color. 
			// Pieces of the same color may not take each other.
			return false;
		}

		return true;
	}

	/** Returns true if Square at location (x,y) is a valid location on the board.
	  * (The board is 8x8, so valid locations include 0 <= x <= 7 and 0 <= y <= 7.)
	  * Returns false otherwise. */
	public boolean inBounds(int x, int y) {
		return (x >= 0) && (x <= 7) && (y >= 0) && (y <= 7);
	}

}
