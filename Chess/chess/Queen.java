package chess;

import java.util.HashSet;

public class Queen extends Piece {

	public Queen(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "queen";
		this.findSquaresAttacking();
	}

	@Override
	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

		// Queens can move in any direction.

		// Get attacking squares North of the Queen.
		for (int y = this.y + 1; inBounds(this.x, y); y++) {
			attacking.add(board.getSquare(this.x, y));
		}

		// Get attacking squares South of the Queen.
		for (int y = this.y - 1; inBounds(this.x, y); y--) {
			attacking.add(board.getSquare(this.x, y));
		}

		// Get attacking squares West of the Queen.
		for (int x = this.x - 1; inBounds(x, this.y); x--) {
			attacking.add(board.getSquare(x, this.y));
		}

		// Get attacking squares East of the Queen.
		for (int x = this.x + 1; inBounds(x, this.y); x++) {
			attacking.add(board.getSquare(x, this.y));
		}
		
		// Get attacking squares to the North-East of the Queen.
		for (int x = this.x + 1, y = this.y + 1; inBounds(x, y); x++, y++) {
			attacking.add(board.getSquare(x, y));
		}

		// Get attacking squares to the North-West of the Queen.
		for (int x = this.x - 1, y = this.y + 1; inBounds(x, y); x--, y++) {
			attacking.add(board.getSquare(x, y));
		}

		// Get attacking squares to the South-East of the Queen.
		for (int x = this.x + 1, y = this.y - 1; inBounds(x, y); x++, y--) {
			attacking.add(board.getSquare(x, y));
		}

		// Get attacking squares to the South-West of the Queen.
		for (int x = this.x - 1, y = this.y - 1; inBounds(x, y); x--, y--) {
			attacking.add(board.getSquare(x, y));
		}
	}
}
