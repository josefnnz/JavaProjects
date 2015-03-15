package chess;

import java.util.HashSet;

public class Board {
	private final int size = 8;
	private Square[][] squares;
	private Piece[][] pieces;
	private HashSet<Piece> blackPieces;
	private HashSet<Piece> whitePieces;
	private Piece blackKing;
	private Piece whiteKing;

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

	/** Returns true if moving PIECE to location (x,y) does not put his king in check.
	  * Returns false otherwise. 
	  * Take into account whether THIS piece moving will result in king be in check.
	  * Assumes location (x,y) is valid Square for PIECE to move in regards to that PIECE's
	  * movement regulations. (e.g. Bishops move diagonally. Knights move in L-shape pattern. */
	public boolean validMove(Piece piece, int x, int y) {
		return false;
	}

	// /** Move piece to location (x,y).
	//   * Assumes moving to location (x,y) is a valid move. */
	// public void movePiece(int x, int y) {
	// 	// Remove existing piece from (x,y).
	// 	// If no existing piece, remove method will do nothing.
	// 	board.removePiece(x,y);

	// 	// Place THIS piece at (x,y).
	// 	board.placePiece(this, x, y);

	// 	// Remove THIS piece from original location.
	// 	board.removePiece(this.x, this.y);

	// 	// Update (x,y) location of THIS piece.
	// 	this.x = x;
	// 	this.y = y;

	// 	// Update HashSet ATTACKING to consists of new attacking squares due to the move.
	// 	this.findSquaresAttacking();
	// }

	/** Construct array of Squares. */
	private void createArrayOfSquares() {
		squares = new Square[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				squares[x][y] = new Square(x, y);
			}
		}
	}

	/** Constructs the following:
	  * 1) Array of Pieces 
	  * 2) Set of black pieces.
	  * 3) Set of white pieces.
	  * 4) Black king piece stored.
	  * 5) White king piece stored. */
	private void createArrayOfPieces(boolean isEmpty) {
		pieces = new Piece[size][size];
		blackPieces = new HashSet<Piece>();
		whitePieces = new HashSet<Piece>();
		
		if (isEmpty) {
			// Evaluates if empty board is to be constructed.
			return;
		}

		// Row location for pieces excluding pawns.
		int[] yRows = {0, 7};

		// Row 0 has White pieces. 
		// Row 7 has Black pieces.

		// Place Rooks.
		int[] xRook = {0, 7};
		for (int x : xRook) {
			for (int y : yRows) {
				if (y == 0) {
					pieces[x][y] = new Rook(false, this, x, y);
					whitePieces.add(pieces[x][y]);
				} else {
					pieces[x][y] = new Rook(true, this, x, y);
					blackPieces.add(pieces[x][y]);
				}
			}
		}

		// Place Knights.
		int[] xKnight = {1, 6};
		for (int x : xKnight) {
			for (int y : yRows) {
				if (y == 0) {
					pieces[x][y] = new Knight(false, this, x, y);
					whitePieces.add(pieces[x][y]);
				} else {
					pieces[x][y] = new Knight(true, this, x, y);
					blackPieces.add(pieces[x][y]);
				}
			}
		}

		// Place Bishops.
		int[] xBishop = {2, 5};
		for (int x : xBishop) {
			for (int y : yRows) {
				if (y == 0) {
					pieces[x][y] = new Bishop(false, this, x, y);
					whitePieces.add(pieces[x][y]);
				} else {
					pieces[x][y] = new Bishop(true, this, x, y);
					blackPieces.add(pieces[x][y]);
				}
			}
		}

		// Place Queens.
		int xQueen = 4;
		for (int y : yRows) {
			if (y == 0) {
				pieces[xQueen][y] = new Queen(false, this, xQueen, y);
				whitePieces.add(pieces[xQueen][y]);
			} else {
				pieces[xQueen][y] = new Queen(true, this, xQueen, y);
				blackPieces.add(pieces[xQueen][y]);
			}
		}

		// Place Kings.
		int xKing = 3;
		for (int y : yRows) {
			if (y == 0) {
				whiteKing = new King(false, this, xKing, y);
				pieces[xKing][y] = whiteKing;
				whitePieces.add(whiteKing);
			} else {
				blackKing = new King(true, this, xKing, y);
				pieces[xKing][y] = blackKing;
				blackPieces.add(blackKing);
			}
		}

		// Place Pawns.
		// Pawns only lie in rows 1 and 6. 
		// Pawns are placed on each square in rows 1 and 6.
		int[] yPawn = {1, 6};
		for (int y : yPawn) {
			for (int x = 0; x < size; x++) {
				if (y == 1) {
					pieces[x][y] = new Pawn(false, this, x, y);
					whitePieces.add(pieces[x][y]);
				} else {
					pieces[x][y] = new Pawn(true, this, x, y);
					blackPieces.add(pieces[x][y]);
				}
			}
		}

		// Call findAttackingSquares on all pieces on board. 
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Piece current = pieces[x][y];
				if (current != null) {
					pieces[x][y].findSquaresAttacking();
				}
			}
		}
	}

	public void drawBoard() {
		StdDrawPlus.setXscale(0, size);
		StdDrawPlus.setYscale(0, size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
                }                

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                drawPieceOnBoard(i, j);
            }
        }
	}

	public void drawPieceOnBoard(int i, int j) {
		Piece piece = pieces[i][j];
		if (piece == null) {
			return;
		}  

		String type = piece.getType();
		String color = "white";
		if (piece.isBlack()) {
			color = "black";
		}

		StdDrawPlus.picture(i + .5, j + .5, "img/" + color + "_" + type + ".png", 1, 1);
	}

}
