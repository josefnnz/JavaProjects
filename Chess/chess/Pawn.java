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
		this.findSquaresAttacking();
	}

	@Override
	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

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
				attacking.add(board.getSquare(x, yAhead));
			}
		}

		// Can move two squares on initial move.
		if (!hasMoved) {
			attacking.add(board.getSquare(this.x, this.y + 2 * verticalShift));
		}
	}

	@Override
	public void move(int x, int y) {
		super.move(x, y);
		hasMoved = true;
	}
}
