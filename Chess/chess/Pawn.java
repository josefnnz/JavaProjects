package chess;

import java.util.HashSet;

public class Pawn extends Piece {
	public boolean hasMoved;

	public Pawn(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "pawn";
		this.hasMoved = false;
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
		if (this.isBlack) {
			verticalShift = -1;
		}

		int yAhead = this.y + verticalShift;
		for (int x : xValues) {
			if (inBounds(x, yAhead)) {
				squareOccupant = board.getPiece(x, yAhead);
				if (squareOccupant == null) {
					// Evaluates if Square is empty.
					if (this.x == x) {
						// Evaluates if Pawn is moving forward.
						// Pawn cannot move to an empty diagonal Square.
						attacking.add(board.getSquare(x, yAhead));
					}
				} else if ((squareOccupant.isBlack() != this.isBlack()) && (this.x != x)) {
					// Evaluates if location (x,y) is occupied by an opposing piece and
					// if location (x,y) is diagonal from pawn.
						attacking.add(board.getSquare(x, yAhead));
				}
				// Do nothing if Pawn is blocked by a piece in front of it, or no
				// opposing pieces diagonally in front of it to take.
			}
		}

		// Can move two squares on initial move.
		if (!hasMoved) {
			squareOccupant = board.getPiece(this.x, this.y + 2 * verticalShift);
			if (squareOccupant == null) {
				// Evaluates if Square two spaces ahead of Pawn is empty.
				attacking.add(board.getSquare(this.x, this.y + 2 * verticalShift));
			}
		}
	}

}
