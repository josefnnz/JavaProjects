package chess;

import java.util.ArrayList;

public class King extends Piece {

	public King(boolean isBlack, Board board, int x, int y) {
		this.isBlack = isBlack;
		this.board = board;
		this.x = x;
		this.y = y;
		this.type = "king";
		this.findSquaresAttacking();
	}

	public void findSquaresAttacking() {
		
	}
}
