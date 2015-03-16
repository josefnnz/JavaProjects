package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import chess.*;
import java.util.HashSet;

public class TestPiece {

	@Test
	public void testKingCanCastle() {
		Board b = new Board(true);
		Piece whiteKing = new King(false, b, 3, 0);
		Piece rookLeft = new Rook(false, b, 0, 0);
		Piece rookRight = new Rook(false, b, 7, 0);
		for (int i = 0; i < 8; i++) {
			b.placePiece(new Pawn(false, b, i, 1), i, 1);
		}
		b.placePiece(new Queen(false, b, 4, 0), 4, 0);
		b.placePiece(whiteKing, 3, 0);
		b.placePiece(rookLeft, 0, 0);
		b.placePiece(rookRight, 7, 0);
		b.findSquaresAttackingForAllPieces();		

		HashSet<Square> expected = new HashSet<>();
		expected.add(new Square(2, 0));
		expected.add(new Square(1, 0));

		assertEquals(expected, whiteKing.getAttacking());
	}

	@Test
	public void testKnightFindAttackingSquares() {
		Board b = new Board(false);
		Piece knight = b.getPiece(1, 0);

		HashSet<Square> expected = new HashSet<>();
		expected.add(new Square(2, 2));
		expected.add(new Square(0, 2));

		assertEquals(expected, knight.getAttacking());
	}

	@Test
	public void testBishopFindAttackingSquares() {
		Board b = new Board(false);
		Piece bishop = b.getPiece(2, 0);

		HashSet<Square> expected = new HashSet<>();

		assertEquals(expected, bishop.getAttacking());
	}

	@Test
	public void testRookFindAttackingSquares() {
		Board b = new Board(false);
		Piece rook = b.getPiece(0, 0);

		HashSet<Square> expected = new HashSet<>();

		assertEquals(expected, rook.getAttacking());
	}

	@Test
	public void testKingFindAttackingSquares() {
		Board b = new Board(false);
		Piece king = b.getPiece(3, 0);

		HashSet<Square> expected = new HashSet<>();

		assertEquals(expected, king.getAttacking());
	}

	@Test
	public void testQueenFindAttackingSquares() {
		Board b = new Board(false);
		Piece queen = b.getPiece(4, 0);

		HashSet<Square> expected = new HashSet<>();

		assertEquals(expected, queen.getAttacking());
	}

	@Test
	public void testPawnFindAttackingSquares() {
		Board b = new Board(false);
		Piece pawn = b.getPiece(0, 1);

		HashSet<Square> expected = new HashSet<>();
		expected.add(new Square(0, 2));
		expected.add(new Square(0, 3));

		assertEquals(expected, pawn.getAttacking());
	}

	@Test
	public void testWhitePawnFindAttackingSquareWithOpposingPieceDiagonal() {
		Board b = new Board(false);
		Piece pawn = b.getPiece(0, 1);
		b.movePiece(pawn, 0, 5);
		pawn.findSquaresAttacking();

		HashSet<Square> expected = new HashSet<>();
		expected.add(new Square(1, 6));

		assertEquals(expected, pawn.getAttacking());
	}

	@Test
	public void testValidMoveKnight() {
		Board b = new Board(false);
		Piece knight = b.getPiece(1, 0);

		assertTrue(knight.validMove(2, 2));
		assertTrue(knight.validMove(0, 2));
		assertFalse(knight.validMove(3, 1));
		assertFalse(knight.validMove(-1, 0));
		assertFalse(knight.validMove(0, -1));
		assertFalse(knight.validMove(8, 0));
		assertFalse(knight.validMove(0, 8));
	}

	/* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(TestPiece.class);
    }    

}
