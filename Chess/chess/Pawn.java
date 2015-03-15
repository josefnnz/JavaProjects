package chess;

import java.util.HashSet;

public class Pawn extends Piece {
	/** Hold original y-coordinate for pawn. When y-yOriginal != 0, pawn has moved. */
	public int yOriginal;

	public Pawn(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "pawn";
		this.yOriginal = y;
	}

	@Override
	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

		// Holds piece in location (x,y).
		Piece squareOccupant = null;

		// Pawns usually can only move one square forward or diagonally.
		// However, on pawn's initial move, can move forward two squares.
	
		int[] xValues = {this.x - 1, this.x, this.x + 1};

		// Black moves downward. White moves upward.
		int verticalShift = 1;
		if (this.isBlack()) {
			verticalShift = -1;
		}

		int yAhead = this.y + verticalShift;
		for (int x : xValues) {
			if (inBounds(x, yAhead)) {
				squareOccupant = board.getPiece(x, yAhead);
				if (this.x == x) {
					// Evaluates if Pawn is moving directly forward (not diagonally).
					if (squareOccupant == null) {
						// Evaluates if square in front of Pawn is empty.
						attacking.add(board.getSquare(x, yAhead));
					}
				} else {
					// Evaluates if Pawn is moving diagonally.
					if ((squareOccupant != null) && (squareOccupant.isBlack() != this.isBlack())) {
						// Evaluates if Square diagonal from Pawn is occupied by opposing piece.
						attacking.add(board.getSquare(x, yAhead));
					}
				}
			}
		}

		// Can move two squares on initial move.
		boolean hasNeverMoved = ((y - yOriginal) == 0);
		if (hasNeverMoved) {
			Piece oneSquareAheadOccupant = board.getPiece(this.x, this.y + verticalShift);
			squareOccupant = board.getPiece(this.x, this.y + 2 * verticalShift);
			if ((oneSquareAheadOccupant == null) && (squareOccupant == null)) {
				// Evaluates if both Squares one and two spaces ahead of Pawn are empty.
				attacking.add(board.getSquare(this.x, this.y + 2 * verticalShift));
			}
		}
	}

}
