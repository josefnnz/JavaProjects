package chess;

import java.util.HashSet;

public class Board {
	private final int size = 8;
	private Square[][] squares;
	private Piece[][] pieces;
	public HashSet<Piece> blackPieces;
	public HashSet<Piece> blackPiecesRemoved = new HashSet<>();
	public HashSet<Piece> whitePieces;
	public HashSet<Piece> whitePiecesRemoved = new HashSet<>();
	public Piece blackKing;
	public Piece whiteKing;

	public Board(boolean isEmpty) {
		createArrayOfSquares();
		createArrayOfPieces(isEmpty);
	}

	/** Place piece at location (x,y).
	  * Assumes (x,y) is an empty square. */
	public void placePiece(Piece p, int x, int y) {
		// Add P to set of active pieces.
		// Remove P from set of inactive pieces.
		// If P not in set of inactive pieces, nothing is done.
		pieces[x][y] = p;

		if (p == null) {
			return;
		}

		// if (p.isBlack()) {
		// 	blackPieces.add(p);
		// 	blackPiecesRemoved.remove(p);
		// } else {
		// 	whitePieces.add(p);
		// 	whitePiecesRemoved.remove(p);
		// }
	}

	/** Return piece at location (x,y). */
	public Piece getPiece(int x, int y) {
		return pieces[x][y];
	}

	/** Remove piece at location (x,y) from board. */
	public Piece removePiece(int x, int y) {
		Piece squareOccupant = pieces[x][y];
		pieces[x][y] = null;
		return squareOccupant;
	}

	/** Return Square at location (x,y). */
	public Square getSquare(int x, int y) {
		return squares[x][y];
	}

	/** Return size of the board. */
	public int getSize() {
		return size;
	}

	/** Return true if King is in check.
	  * Return false otherwise. */
	public boolean isKingInCheck(boolean isBlack) {
		// // Choose desired King and opposition's pieces.
		Piece king = whiteKing;
		// HashSet<Piece> oppositionPieces = blackPieces;
		if (isBlack) {
			king = blackKing;
		// 	oppositionPieces = whitePieces;
		}

		HashSet<Piece> oppositionPieces = new HashSet<>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Piece pieceInCurrentSquare = pieces[i][j];
				if (pieceInCurrentSquare != null) {
					if (pieceInCurrentSquare.isBlack() != isBlack) {
						oppositionPieces.add(pieceInCurrentSquare);
					}
				}
			}
		}

		// Gather all attacking spaces of Black pieces.
		HashSet<Square> allOppositionAttackingSpaces = new HashSet<>();
		for (Piece p : oppositionPieces) {
			allOppositionAttackingSpaces.addAll(p.getAttacking());
		}

		Square kingLocation = new Square(king.getX(), king.getY());

		return allOppositionAttackingSpaces.contains(kingLocation);

	}

	public boolean isKingInCheckMate(boolean isBlack) {
		// Check if King isBlack is in check.  
		// If not, return false.
		// Otherwise, check to see if any isBlack piece has a move
		// that results in the king not being in check.
		if (!this.isKingInCheck(isBlack)) {
			return false;
		}

		// Gather all pieces of isBlack color.
		HashSet<Piece> allPiecesOfIsBlack = new HashSet<>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Piece pieceInCurrentSquare = pieces[i][j];
				if (pieceInCurrentSquare != null) {
					if (pieceInCurrentSquare.isBlack() == isBlack) {
						allPiecesOfIsBlack.add(pieceInCurrentSquare);
					}
				}
			}
		}

		// Check to see if any of the pieces can move and result in its
		// king not being in check.
		for (Piece current : allPiecesOfIsBlack) {
			HashSet<Square> currentSquaresAttacking = current.getAttacking();
			for (Square s : currentSquaresAttacking) {
				boolean hasValidMove = this.validMove(current, s.getX(), s.getY());
				if (hasValidMove) {
					return false;
				}
			}
		}

		return true;
	}

	/** Returns true if moving PIECE to location (x,y) does not put his king in check.
	  * Returns false otherwise. 
	  * Take into account whether THIS piece moving will result in king be in check.
	  * Assumes location (x,y) is valid Square for PIECE to move in regards to that PIECE's
	  * movement regulations. (e.g. Bishops move diagonally. Knights move in L-shape pattern. */
	public boolean validMove(Piece p, int x, int y) {
		if (!p.validMove(x, y)) {
			// Evaluates if piece P cannot move to (x,y).
			return false;
		}

		// Evaluates if piece P can move to (x,y).
		// Now need to check that moving piece P to (x,y)
		// does not put that piece's King in check.

		// Record original location of piece P.
		int xOriginal = p.getX();
		int yOriginal = p.getY();

		// Determine what color piece is moving.
		boolean isBlack = p.isBlack();

		// Move piece P to target location (x,y).
		Piece pieceRemoved = this.movePiece(p, x, y);

		// With piece moved, call findSquaresAttackingForAllPieces.
		this.findSquaresAttackingForAllPieces();

		// Determine if piece P moving to location (x,y)
		// placed its King under check.
		boolean isKingInCheck = this.isKingInCheck(isBlack);

		// Move piece P back to original location.
		this.movePiece(p, xOriginal, yOriginal);

		// Place REMOVEDPIECE back on board.
		this.placePiece(pieceRemoved, x, y);

		// With piece's placed back in place, call findSquaresAttackingForAllPieces.
		this.findSquaresAttackingForAllPieces();

		// Return true if move did not result in the King being in check.
		// i.e. Return true if IsKingInCheck is false.
		//      Return false if isKingInCheck is true.
		return !isKingInCheck;
	}

	/** Move piece P to new location (x,y). 
	  * Return Piece that was removed from location (x,y).
	  * If no Piece was removed, return null.  */
	public Piece movePiece(Piece p, int x, int y) {
		int xOriginal = p.getX();
		int yOriginal = p.getY();

		// Remove piece from target location.
		// If no piece on target location, this will return null.
		Piece pieceRemoved = this.removePiece(x, y);

		// Change (x,y) location on PIECE.
		p.setX(x);
		p.setY(y);

		// Move piece to new location on board.
		this.placePiece(p, x, y);

		// Remove piece from old location on board.
		this.removePiece(xOriginal, yOriginal);

		// Remove PIECEREMOVED from set of active pieces.
		// // Place PIECEREMOVED in set of inactive pieces.
		// if (pieceRemoved != null) {
		// 	if (pieceRemoved.isBlack()) {
		// 		blackPieces.remove(pieceRemoved);
		// 		blackPiecesRemoved.add(pieceRemoved);
		// 	} else {
		// 		whitePieces.remove(pieceRemoved);
		// 		whitePiecesRemoved.add(pieceRemoved);
		// 	}
		// }
		this.findSquaresAttackingForAllPieces();

		return pieceRemoved;
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

	public void findSquaresAttackingForAllPieces() {
		// Call findSquaresAttacking on all pieces on board. 
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Piece current = pieces[x][y];
				if (current != null) {
					pieces[x][y].findSquaresAttacking();
				}
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
		this.findSquaresAttackingForAllPieces();
	}

	/** Draws board with isBlack pieces at the bottom of the board. */
	public void drawBoard(boolean isBlack) {
		StdDrawPlus.setXscale(0, size);
		StdDrawPlus.setYscale(0, size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if (!isBlack) {
	                if ((i + j) % 2 == 0) {
	                	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
	                } else {
	                	StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
	                }            
            	} else {
	                if ((i + j) % 2 == 0) {
	                	StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
	                } else {
	                	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
	                }                      	
	            }

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }

        for (int i = 0; i < size; i++) {
        	for (int j = 0; j < size; j++) {
        		drawPieceOnBoard(isBlack, i, j);
        	}
        }
	}

	public void drawPieceOnBoard(boolean isBlack, int i, int j) {
		Piece piece = pieces[i][j];
		if (piece == null) {
			return;
		}  

		if (isBlack) {
			j = Math.abs(7 - j);
		}

		String type = piece.getType();
		String color = "white";
		if (piece.isBlack()) {
			color = "black";
		}

		StdDrawPlus.picture(i + .5, j + .5, "img/" + color + "_" + type + ".png", 1, 1);
	}

}
