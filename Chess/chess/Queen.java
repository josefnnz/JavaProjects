package chess;

import java.util.HashSet;

public class Queen extends Piece {

	public Queen(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "queen";
	}

	@Override
	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

		// Holds piece in location (x,y).
		Piece squareOccupant = null;

		// Queens can move in any direction.

		// Get attacking squares to the North-East of the Queen.
		for (int x = this.x + 1, y = this.y + 1; inBounds(x, y); x++, y++) {
			squareOccupant = board.getPiece(x, y);
			if (squareOccupant == null) {
				// Evaluates if Square is empty.
				attacking.add(board.getSquare(x, y));
			} else if (squareOccupant.isBlack() != this.isBlack()) {
				// Evaluates if current piece in Square is an opponent 
				// for THIS piece. Breaks because Queen cannot move
				// through a piece. It is blocked by the piece at (x,y).
				attacking.add(board.getSquare(x, y));
				break;
			} else {
				// Evaluates if current piece in Square is on same team as THIS piece.
				break;
			}
		}

		// Get attacking squares to the North-West of the Queen.
		for (int x = this.x - 1, y = this.y + 1; inBounds(x, y); x--, y++) {
			squareOccupant = board.getPiece(x, y);
			if (squareOccupant == null) {
				attacking.add(board.getSquare(x, y));
			} else if (squareOccupant.isBlack() != this.isBlack()) {
				attacking.add(board.getSquare(x, y));
				break;
			} else {
				break;
			}
		}

		// Get attacking squares to the South-East of the Queen.
		for (int x = this.x + 1, y = this.y - 1; inBounds(x, y); x++, y--) {
			squareOccupant = board.getPiece(x, y);
			if (squareOccupant == null) {
				attacking.add(board.getSquare(x, y));
			} else if (squareOccupant.isBlack() != this.isBlack()) {
				attacking.add(board.getSquare(x, y));
				break;
			} else {
				break;
			}
		}

		// Get attacking squares to the South-West of the Queen.
		for (int x = this.x - 1, y = this.y - 1; inBounds(x, y); x--, y--) {
			squareOccupant = board.getPiece(x, y);
			if (squareOccupant == null) {
				attacking.add(board.getSquare(x, y));
			} else if (squareOccupant.isBlack() != this.isBlack()) {
				attacking.add(board.getSquare(x, y));
				break;
			} else {
				break;
			}
		}

		// Get attacking squares North of the Queen.
		for (int y = this.y + 1; inBounds(this.x, y); y++) {
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

		// Get attacking squares South of the Queen.
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

		// Get attacking squares West of the Queen.
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

		// Get attacking squares East of the Queen.
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
