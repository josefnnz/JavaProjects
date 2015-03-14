package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import chess.Square;
import java.util.ArrayList;

public class TestSquare {

	@Test
	public void testEqualsWithTwoEqualSquares() {
		Square s = new Square(1, 1);
		assertTrue(s.equals(new Square(1, 1)));
	}

	@Test
	public void testEqualsWithTwoNonEqualSquares() {
		Square s = new Square(1, 1);
		assertFalse(s.equals(new Square(2, 3)));
		assertFalse(s.equals(new Square(1, 2)));
		assertFalse(s.equals(new Square(2, 1)));
	}

	@Test
	public void testSearchingForSquareInArrayList() {
		ArrayList<Square> squareList = new ArrayList<>();
		squareList.add(new Square(1, 1));
		squareList.add(new Square(2, 2));
		squareList.add(new Square(3, 3));

		assertTrue(squareList.contains(new Square(1, 1)));
		assertTrue(squareList.contains(new Square(2, 2)));
		assertTrue(squareList.contains(new Square(3, 3)));

		assertFalse(squareList.contains(new Square(4, 4)));
	}

	/* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(TestSquare.class);
    }    
}
