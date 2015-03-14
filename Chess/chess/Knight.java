package chess;

import java.util.HashSet;

public class Knight extends Piece {

	public Knight(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "knight";
		this.findSquaresAttacking();
	}

	@Override
	public void findSquaresAttacking() {
		// Create empty HashSet to place current attacking squares in.
		attacking = new HashSet<Square>();

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
					attacking.add(board.getSquare(x, y));
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
					attacking.add(board.getSquare(x, y));
				}
			}
		}
	}

}
 