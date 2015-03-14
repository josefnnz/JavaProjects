package chess;

import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "rook";
		this.findSquaresAttacking();
	}

	public void findSquaresAttacking() {
		
	}
}
