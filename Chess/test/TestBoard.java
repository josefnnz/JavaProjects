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

	/* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(TestBoard.class);
    }    

}