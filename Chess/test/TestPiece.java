package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import chess.*;
import java.util.HashSet;

public class TestPiece {

	@Test
	public void testKnightFindAttackingSquares() {
		Board b = new Board(false);
		Piece knight = b.getPiece(1, 0);

		HashSet<Square> expected = new HashSet<>();
		expected.add(new Square(2, 2));
		expected.add(new Square(0, 2));
		expected.add(new Square(3, 1));

		assertEquals(expected, knight.getAttacking());
	}

	@Test
	public void testBishopFindAttackingSquares() {
		Board b = new Board(false);
		Piece bishop = b.getPiece(2, 0);

		HashSet<Square> expected = new HashSet<>();
		expected.add(new Square(1, 1));
		expected.add(new Square(0, 2));
		expected.add(new Square(3, 1));
		expected.add(new Square(4, 2));
		expected.add(new Square(5, 3));
		expected.add(new Square(6, 4));
		expected.add(new Square(7, 5));

		assertEquals(expected, bishop.getAttacking());
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
