package chess;

import java.util.HashSet;

public class Bishop extends Piece {

	public Bishop(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "bishop";
		this.findSquaresAttacking();
	}

	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

		// Bishops can only move diagonally.

		// Get attacking squares to the North-East of the Bishop.
		for (int x = this.x + 1, y = this.y + 1; inBounds(x, y); x++, y++) {
			attacking.add(board.getSquare(x, y));
		}

		// Get attacking squares to the North-West of the Bishop.
		for (int x = this.x - 1, y = this.y + 1; inBounds(x, y); x--, y++) {
			attacking.add(board.getSquare(x, y));
		}

		// Get attacking squares to the South-East of the Bishop.
		for (int x = this.x + 1, y = this.y - 1; inBounds(x, y); x++, y--) {
			attacking.add(board.getSquare(x, y));
		}

		// Get attacking squares to the South-West of the Bishop.
		for (int x = this.x - 1, y = this.y - 1; inBounds(x, y); x--, y--) {
			attacking.add(board.getSquare(x, y));
		}
	}
}
