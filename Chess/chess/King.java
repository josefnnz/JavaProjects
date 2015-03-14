package chess;

import java.util.HashSet;

public class King extends Piece {

	public King(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "king";
		this.findSquaresAttacking();
	}

	@Override
	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

		// Kings can move 1 square in any direction.
		int[] xAdjacent = {this.x - 1, this.x + 1};
		int[] yAdjacent = {this.y - 1, this.y + 1};

		// Get attacking squares diagonal of the King.
		for (int x : xAdjacent) {
			for (int y : yAdjacent) {
				if (inBounds(x, y)) {
					attacking.add(board.getSquare(x, y));
				}
			}
		}

		// Get attacking squares up and down of the King.
		for (int y : yAdjacent) {
			if (inBounds(this.x, y)) {
				attacking.add(board.getSquare(this.x, y));
			}
		}

		// Get attacking squares left and right of the King.
		for (int x : xAdjacent) {
			if (inBounds(x, this.y)) {
				attacking.add(board.getSquare(x, this.y));
			}
		}
	}
}
