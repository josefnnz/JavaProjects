package chess;

public class Board {
	private final int size = 8;
	private Square[][] squares;
	private Piece[][] pieces;

	public Board(boolean isEmpty) {
		createArrayOfSquares();
		createArrayOfPieces(isEmpty);
	}

	/** Place piece at location (x,y).
	  * Assumes (x,y) is an empty square. */
	public void placePiece(Piece p, int x, int y) {
		pieces[x][y] = p;
	}

	/** Return piece at location (x,y). */
	public Piece getPiece(int x, int y) {
		return pieces[x][y];
	}

	/** Remove piece at location (x,y) from board. */
	public void removePiece(int x, int y) {
		pieces[x][y] = null;
	}

	/** Return Square at location (x,y). */
	public Square getSquare(int x, int y) {
		return squares[x][y];
	}

	/** Return size of the board. */
	public int getSize() {
		return size;
	}

	/** Construct array of Squares. */
	private void createArrayOfSquares() {
		squares = new Square[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				squares[x][y] = new Square(x, y);
			}
		}
	}

	/** Construct array of Pieces. */
	private void createArrayOfPieces(boolean isEmpty) {
		pieces = new Piece[size][size];
		
		if (isEmpty) {
			// Evaluates if empty board is to be constructed.
			return;
		}

		// Row location for pieces excluding pawns.
		int[] yRows = {0, 7};

		// Row 0 has White pieces. 
		// Row 7 has Black pieces.
		// Use boolean to keep track of whether current piece placing is Black or White.
		boolean isBlack = true;

		// Place Rooks.
		int[] xRook = {0, 7};
		for (int x : xRook) {
			for (int y : yRows) {
				if (y == 0) {
					isBlack = false;
				} else {
					isBlack = true;
				}
				pieces[x][y] = new Rook(isBlack, this, x, y);
			}
		}

		// Place Knights.
		int[] xKnight = {1, 6};
		for (int x : xKnight) {
			for (int y : yRows) {
				if (y == 0) {
					isBlack = false;
				} else {
					isBlack = true;
				}
				pieces[x][y] = new Knight(isBlack, this, x, y);
			}
		}

		// Place Bishops.
		int[] xBishop = {2, 5};
		for (int x : xBishop) {
			for (int y : yRows) {
				if (y == 0) {
					isBlack = false;
				} else {
					isBlack = true;
				}
				pieces[x][y] = new Bishop(isBlack, this, x, y);
			}
		}

		// Place Queens.
		int xQueen = 4;
		for (int y : yRows) {
			if (y == 0) {
				isBlack = false;
			} else {
				isBlack = true;
			}
			pieces[xQueen][y] = new Queen(isBlack, this, xQueen, y);
		}

		// Place Kings.
		int xKing = 3;
		for (int y : yRows) {
			if (y == 0) {
				isBlack = false;
			} else {
				isBlack = true;
			}
			pieces[xKing][y] = new King(isBlack, this, xKing, y);
		}

		// Place Pawns.
		// Pawns only lie in rows 1 and 6. 
		// Pawns are placed on each square in rows 1 and 6.
		int[] yPawn = {1, 6};
		for (int y : yPawn) {
			for (int x = 0; x < size; x++) {
				if (y == 1) {
					isBlack = false;
				} else {
					isBlack = true;
				}
				pieces[x][y] = new Pawn(isBlack, this, x, y);
			}
		}
	}

}
