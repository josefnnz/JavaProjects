package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import chess.*;

public class TestBoard {

	@Test
	public void testGetPiece() {
		Board b = new Board(false);
		Piece p = b.getPiece(1, 0);
		assertFalse(p.isBlack());
	}

	@Test
	public void testIsKingInCheckWhenKingIsInCheck() {
		Board b = new Board(true);
		Piece whiteRook = new Rook(false, b, 0, 0);
		whiteRook.findSquaresAttacking();
		Piece blackKing = new King(true, b, 0, 1);
		blackKing.findSquaresAttacking();
		b.placePiece(whiteRook, 0, 0);
		b.placePiece(blackKing, 0, 1);
		b.whitePieces.add(whiteRook);
		b.blackPieces.add(blackKing);
		b.blackKing = blackKing;

		assertTrue(b.isKingInCheck(true));
	}

	@Test
	public void testIsKingInCheckWhenKingIsNotInCheck() {
		Board b = new Board(false);
		assertFalse(b.isKingInCheck(false));
		assertFalse(b.isKingInCheck(true));
	}

	@Test
	public void testValidMoveWhenKingIsInCheckAndCanTakePiecePuttingKingInCheck() {
		Board b = new Board(false);
		b.removePiece(3, 1);
		b.removePiece(4, 1);
		b.removePiece(3, 6);
		b.removePiece(4, 6);

		Piece whiteQueen = b.getPiece(4, 0);
		b.movePiece(whiteQueen, 4, 6);

		Piece blackBishop = b.getPiece(5, 7);
		assertTrue(b.validMove(blackBishop, 4, 6));
	}

	/* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(TestBoard.class);
    }    

}