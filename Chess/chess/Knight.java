package chess;

import java.util.HashSet;

public class Knight extends Piece {

	public Knight(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "knight";
	}

	@Override
	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

		// Holds piece in location (x,y).
		Piece squareOccupant = null;

		// Knights can only move in  L-shape pattern.
		// L-shape must consists of the following in order:
		// 1) Moving up, down, left, or right by 2 squares.
		// 2) Then moving up, down, left, or right by 1 square.

		// Following for loop takes care of cases:
		// 1) Move up or down 2 squares.
		// 2) Then move left or right 1 square.
		int[] oneLeftOrRight = {this.x - 1, this.x + 1};
		int[] twoUpOrDown = {this.y - 2, this.y + 2};
		for (int x : oneLeftOrRight) {
			for (int y : twoUpOrDown) {
				if (inBounds(x, y)) {
					squareOccupant = board.getPiece(x, y);
					if (squareOccupant == null) {
						// Evaluates if Square is empty.
						attacking.add(board.getSquare(x, y));
					} else if (squareOccupant.isBlack() != this.isBlack()) {
						// Evaluates if location (x,y) is occupied by an opposing piece.
						attacking.add(board.getSquare(x, y));
					}
					// Do nothing if location (x,y) is occupied by a piece on the same team.
				}
			}
		}

		// Following for loop takes care of cases:
		// 1) Move left or right 2 squares.
		// 2) Then move up or down 1 square.
		int[] twoLeftOrRight = {this.x - 2, this.x + 2};
		int[] oneUpOrDown = {this.y - 1, this.y + 1};
		for (int x : twoLeftOrRight) {
			for (int y : oneUpOrDown){
				if (inBounds(x, y)) {
					squareOccupant = board.getPiece(x, y);
					if (squareOccupant == null) {
						attacking.add(board.getSquare(x, y));
					} else if (squareOccupant.isBlack() != this.isBlack()) {
						// Evaluates if location (x,y) is occupied by an opposing piece.
						attacking.add(board.getSquare(x, y));
					}
					// Do nothing if location (x,y) is occupied by a piece on the same team.
				}
			}
		}
	}

}
 