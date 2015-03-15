package chess;

import java.util.HashSet;

public class Rook extends Piece {

	public Rook(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "rook";
	}

	@Override
	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

		// Holds piece in location (x,y).
		Piece squareOccupant = null;

		// Rooks can only move up, down, left, or right.

		// Get attacking squares North of the Rook.
		for (int y = this.y + 1; inBounds(this.x, y); y++) {
			squareOccupant = board.getPiece(this.x, y);
			if (squareOccupant == null) {
				// Evaluates if Square is empty.
				attacking.add(board.getSquare(this.x, y));
			} else if (squareOccupant.isBlack() != this.isBlack()) {
				// Evaluates if current piece in Square is an opponent 
				// for THIS piece. Breaks because Rook cannot move
				// through a piece. It is blocked by the piece at (x,y).
				attacking.add(board.getSquare(this.x, y));
				break;
			} else {
				// Evaluates if current piece in Square is on same team as THIS piece.
				break;
			}
		}

		// Get attacking squares South of the Rook.
		for (int y = this.y - 1; inBounds(this.x, y); y--) {
			squareOccupant = board.getPiece(this.x, y);
			if (squareOccupant == null) {
				attacking.add(board.getSquare(this.x, y));
			} else if (squareOccupant.isBlack() != this.isBlack()) {
				attacking.add(board.getSquare(this.x, y));
				break;
			} else {
				break;
			}
		}

		// Get attacking squares West of the Rook.
		for (int x = this.x - 1; inBounds(x, this.y); x--) {
			squareOccupant = board.getPiece(x, this.y);
			if (squareOccupant == null) {
				attacking.add(board.getSquare(x, this.y));
			} else if (squareOccupant.isBlack() != this.isBlack()) {
				attacking.add(board.getSquare(x, this.y));
				break;
			} else {
				break;
			}
		}

		// Get attacking squares East of the Rook.
		for (int x = this.x + 1; inBounds(x, this.y); x++) {
			squareOccupant = board.getPiece(x, this.y);
			if (squareOccupant == null) {
				attacking.add(board.getSquare(x, this.y));
			} else if (squareOccupant.isBlack() != this.isBlack()) {
				attacking.add(board.getSquare(x, this.y));
				break;
			} else {
				break;
			}
		}
	}
}
