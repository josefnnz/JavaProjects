package chess;

import java.util.HashSet;

public class Rook extends Piece {

	public Rook(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "rook";
		this.findSquaresAttacking();
	}

	@Override
	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

		// Rooks can only move up, down, left, or right.

		// Get attacking squares North of the Rook.
		for (int y = this.y + 1; inBounds(this.x, y); y++) {
			attacking.add(board.getSquare(this.x, y));
		}

		// Get attacking squares South of the Rook.
		for (int y = this.y - 1; inBounds(this.x, y); y--) {
			attacking.add(board.getSquare(this.x, y));
		}

		// Get attacking squares West of the Rook.
		for (int x = this.x - 1; inBounds(x, this.y); x--) {
			attacking.add(board.getSquare(x, this.y));
		}

		// Get attacking squares East of the Rook.
		for (int x = this.x + 1; inBounds(x, this.y); x++) {
			attacking.add(board.getSquare(x, this.y));
		}
	}
}
