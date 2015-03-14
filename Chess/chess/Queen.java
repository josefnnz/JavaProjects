package chess;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "queen";
		this.findSquaresAttacking();
	}

	public void findSquaresAttacking() {
		
	}
}
