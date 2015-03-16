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
	public void testIsKingInCheckmate() {
		Board b = new Board(false);
		Piece whiteBishop = b.getPiece(2, 0);
		Piece whiteKnight = b.getPiece(1, 0);
		b.movePiece(whiteBishop, 2, 6);
		b.movePiece(whiteKnight, 1, 4);
		whiteBishop.findSquaresAttacking();
		whiteKnight.findSquaresAttacking();
		assertTrue(b.isKingInCheckMate(true));
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

	@Test
	public void testDrawingBoardDifferentColors() {
		Board b = new Board(true);
		b.drawBoard(false);
        StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
        StdDrawPlus.filledSquare(0 + .5, 0 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
        StdDrawPlus.filledSquare(1 + .5, 0 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
        StdDrawPlus.filledSquare(2 + .5, 0 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
        StdDrawPlus.filledSquare(3 + .5, 0 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        StdDrawPlus.filledSquare(4 + .5, 0 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
        StdDrawPlus.filledSquare(5 + .5, 0 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
        StdDrawPlus.filledSquare(6 + .5, 0 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
        StdDrawPlus.filledSquare(7 + .5, 0 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.ORANGE);
        StdDrawPlus.filledSquare(0 + .5, 1 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.PINK);
        StdDrawPlus.filledSquare(1 + .5, 1 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(2 + .5, 1 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(3 + .5, 1 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
        StdDrawPlus.filledSquare(4 + .5, 1 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
        StdDrawPlus.filledSquare(5 + .5, 1 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
        StdDrawPlus.filledSquare(6 + .5, 1 + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
        StdDrawPlus.filledSquare(7 + .5, 1 + .5, .5);
   	}

	/* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(TestBoard.class);
    }    

}