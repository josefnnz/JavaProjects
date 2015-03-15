package chess;

import java.util.HashSet;

public class King extends Piece {

	public King(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "king";
	}

	@Override
	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

		// Holds piece in location (x,y).
		Piece squareOccupant = null;

		// Kings can move 1 square in any direction.
		int[] xAdjacent = {this.x - 1, this.x + 1};
		int[] yAdjacent = {this.y - 1, this.y + 1};

		// Get attacking squares diagonal of the King.
		for (int x : xAdjacent) {
			for (int y : yAdjacent) {
				if (inBounds(x, y)) {
					squareOccupant = board.getPiece(x, y);
					if (squareOccupant == null) {
						// Evaluates if Square is empty.
						attacking.add(board.getSquare(x, y));
					} else if (squareOccupant.isBlack() != this.isBlack()) {
						// Evaluates if current piece in Square is an opponent
						// for THIS piece. 
						attacking.add(board.getSquare(x, y));
					}
					// Do nothing if location (x,y) is occupied by a piece on the same team.
				}
			}
		}

		// Get attacking squares up and down of the King.
		for (int y : yAdjacent) {
			if (inBounds(this.x, y)) {
				squareOccupant = board.getPiece(this.x, y);
				if (squareOccupant == null) {
					// Evaluates if Square is empty.
					attacking.add(board.getSquare(this.x, y));
				} else if (squareOccupant.isBlack() != this.isBlack()) {
					// Evaluates if current piece in Square is an opponent
					// for THIS piece. 
					attacking.add(board.getSquare(this.x, y));
				}
				// Do nothing if location (x,y) is occupied by a piece on the same team.
			}
		}

		// Get attacking squares left and right of the King.
		for (int x : xAdjacent) {
			if (inBounds(x, this.y)) {
				squareOccupant = board.getPiece(x, this.y);
				if (squareOccupant == null) {
					// Evaluates if Square is empty.
					attacking.add(board.getSquare(x, this.y));
				} else if (squareOccupant.isBlack() != this.isBlack()) {
					// Evaluates if current piece in Square is an opponent
					// for THIS piece. 
					attacking.add(board.getSquare(x, this.y));
				}
				// Do nothing if location (x,y) is occupied by a piece on the same team.
			}
		}
	}
}
