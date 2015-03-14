package chess;

import java.util.ArrayList;

public class Pawn extends Piece {

	public Pawn(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "pawn";
		this.findSquaresAttacking();
	}

	public void findSquaresAttacking() {
		
	}
}
