package chess;

import java.util.Stack;

public class PlayChessGame {
    // Board that holds current game state (i.e. location of all pieces).
    private Board board;
    // Keep track of the current player.
    private boolean isBlack;
    // Piece current player has selected.
    private Piece selectedPiece;
    // Store (x,y) coordinates of piece currently selected.
    private int xSelected;
    private int ySelected;
    // Determine if current player has selected a piece.
    private boolean hasSelectedPiece;
    // Determine if current player has moved a piece, but not definitively.
    // i.e. player can undo move, and choose to do another move
    private boolean hasMovedPiece;
    // Hold history of all moves in Chess game.
    private Stack<MoveEntry> gameLog = new Stack<MoveEntry>(); 

	public static void main(String[] args) {
        PlayChessGame game = new PlayChessGame();
        Board b = game.getBoard();
        int x = 20;
        int y = 20;

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {    		
            b.drawBoard();

            if (StdDrawPlus.mousePressed()) {
                double xDouble = StdDrawPlus.mouseX();
                double yDouble = StdDrawPlus.mouseY();
                x = (int) xDouble;
                y = (int) yDouble;
                
                if(game.canSelect(x, y)){
					game.select(x,y);
                }
            }

            if (game.hasSelectedPiece()) {
                // Get selected Piece and its (x,y) coordinates.
                Piece selectedPiece = game.selectedPiece();
                int xSelected = selectedPiece.getX();
                int ySelected = selectedPiece.getY();

                // Color in squares where Piece lies and the squares it
                // is permitted to move.
                StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(xSelected + .5, ySelected + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
                if (!game.hasMovedPiece()) {                   
                    for (Square s : selectedPiece.getAttacking()) {
                        int xSquare = s.getX();
                        int ySquare = s.getY();
                        StdDrawPlus.filledSquare(xSquare + .5, ySquare + .5, .5);
                        b.drawPieceOnBoard(xSquare, ySquare);
                    }
                } else {
                    StdDrawPlus.filledSquare(game.xSelected() + .5, game.ySelected() + .5, .5);
                }

                b.drawPieceOnBoard(xSelected, ySelected);
            }          

            if (StdDrawPlus.isSpacePressed() && game.hasMovedPiece()) {
                // Switch players.
                game.endTurn(); 
                // Check if current player has been checkmated or not.
                if (game.isGameOver()) {
                    StdDrawPlus.setXscale(0, 8);
                    StdDrawPlus.setYscale(0,8);
                    StdDrawPlus.picture(4, 4, "img/checkmate.jpg", 8, 8);
                    StdDrawPlus.show(5000);
                }
            	// won = b.winner();
            }
            	
            StdDrawPlus.show(100);
        }
	}

    public PlayChessGame() {
        board = new Board(false);
        StdDrawPlus.setXscale(0, board.getSize());
        StdDrawPlus.setYscale(0, board.getSize());
    }

    public Board getBoard() {
        return board;
    }

    public Piece selectedPiece() {
        return selectedPiece;
    }

    public int xSelected() {
        return xSelected;
    }

    public int ySelected() {
        return ySelected;
    }

    public boolean hasSelectedPiece() {
        return hasSelectedPiece;
    }

    public boolean hasMovedPiece() {
        return hasMovedPiece;
    }

    /** Returns true if current player can select location (x,y).
      * More specifically, returns true if one of the following occurs:
      * 1) If the current player has not selected a piece, and he clicks 
      *    on a location where one of his players resides.
      * 2) If the current player has selected a piece, and clicks on another
      *    one of his pieces (i.e. switches pieces to move).
      * 3) If the current player has selected a piece, and clicks on a location
      *    where the currently selected piece is permitted to move.
      * 4) If the current player has moved a selected piece, but decides to move
      *    it back to its original location.
      */ 
    public boolean canSelect(int x, int y) {
        // Update attacking squares for all pieces.
        board.findSquaresAttackingForAllPieces();

        // If user clicks out of bounds and has not moved a piece,
        // unselect current selected piece.  But return false.
        // This unhighlights the squares where a piece can move.
        if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
            if (hasSelectedPiece && !hasMovedPiece) {
                selectedPiece = null;
                xSelected = -1;
                ySelected = -1;
                hasSelectedPiece = false;
            }
            return false;
        }
        
        Piece squareOccupant = board.getPiece(x, y);

        if (!hasSelectedPiece) {
            // Evaluates if player has not selected a piece.
            if (squareOccupant == null || (squareOccupant.isBlack() != isBlack)) {
                // Evaluates if player tries to select an empty square, 
                // or tries to select a square where an opponent's piece resides.
                return false;
            } 

            // Evaluates if player is trying to select a non-empty square
            // where one of his pieces reside.
            return true;
        }

        // Evaluates if player has already selected a piece.

        if (hasMovedPiece) {
            // Evaluates if player has already moved a piece. 
            // Player's only option is to undo move.
            // i.e. Player must click on original location of moved piece.
            MoveEntry previousMove = gameLog.peek();
            int xPrevious = previousMove.xInitial();
            int yPrevious = previousMove.yInitial();
            return (x == xPrevious) && (y == yPrevious);
        }

        // Evaluates if player has already selected a piece and has not moved it.

        if ((squareOccupant == null) || (squareOccupant.isBlack() != isBlack)) {
            // Evaluates if trying to select an empty square,
            // or trying to select a square where an opponent's piece resides.
            return board.validMove(selectedPiece, x, y);
        }

        // Evaluates if trying to select a non-empty square where
        // a piece on the same team resides.
        return true;

    }

    /** Selects location (x,y) and does any necessary action in doing so.
      * Assumes location (x,y) can be selected.
      * Action select() provides in given situation:
      * 1) If the current player has not selected a piece, and he clicks 
      *    on a location where one of his players resides, select piece he clicked on.
      * 2) If the current player has selected a piece, and clicks on another
      *    one of his pieces (i.e. switches pieces to move), select piece he clicked on.
      * 3) If the current player has selected a piece, and clicks on a location
      *    where the currently selected piece is permitted to move, move piece to that location.
      * 4) If the current player has moved a selected piece, but decides to move
      *    it back to its original location, move piece back to original location.
      */ 
    public void select(int x, int y) {
        Piece squareOccupant = board.getPiece(x, y);

        if (squareOccupant == null) {
            // Evaluates if player is selecting an emtpy Square.
            // This may happen in the following cases:
            // 1) Player has selected a piece, and is moving it.
            // 2) Player has selected a piece, has moved it, but has decided to move it back to
            //    its original location.
            // This only happens when the player has already selected a piece.
            // Guaranteed that piece has already been selected by the canSelect() method.
            // First, record this new move in the GAMELOG.
            // Then, move SELECTEDPIECE to new (x,y) position.
            // Guaranteed that Square (x,y) is a valid move location
            // for SQUAREOCCUPANT because canSelect() already ensured it.
            if (!hasMovedPiece) {
                // Evaluates if player has selected a piece and is choosing a location
                // to move the piece for the first time.

                // Enter this move into GAMELOG.
                int xInitial = selectedPiece.getX();
                int yInitial = selectedPiece.getY();
                MoveEntry moveToExecute = new MoveEntry(selectedPiece, xInitial, yInitial, null, x, y);
                gameLog.push(moveToExecute);

                // Move piece on board.
                board.movePiece(selectedPiece, x, y);

                // Record that piece was moved.
                hasMovedPiece = !hasMovedPiece;

                // Update squares attacking for all pieces after move made.
                board.findSquaresAttackingForAllPieces();
                return;
            }

            // Evaluates if player has selected a piece, moved it,
            // and is moving it back to its original location.
            MoveEntry previousMove = gameLog.pop();
            int xInitial = previousMove.xInitial();
            int yInitial = previousMove.yInitial();
            int xTerminal = previousMove.xTerminal();
            int yTerminal = previousMove.yTerminal();
            Piece pieceTaken = previousMove.getPieceRemoved();

            // Move SELECTEDPIECE back to its original location
            board.movePiece(selectedPiece, xInitial, yInitial);

            // Place PIECETAKEN back on board at its previous location.
            board.placePiece(pieceTaken, xTerminal, yTerminal);

            // Record that piece was moved back to original location.
            hasMovedPiece = !hasMovedPiece;

            // Update squares attacking for all pieces after move made.
            board.findSquaresAttackingForAllPieces();
            return;
        }

        // Evaluates if SQUAREOCCUPANT is not null.

        if (squareOccupant.isBlack() != isBlack) {
            // Evaluates if player is taking an opponent's pieces.
            // This only happens when the player has already selected a piece.
            // Guaranteed that piece has already been selected by the canSelect() method.
            // First, record this new move in the GAMELOG.
            // Then, move SELECTEDPIECE to new (x,y) position.
            // Guaranteed that Square (x,y) is a valid move location
            // for SQUAREOCCUPANT because canSelect() already ensured it.
            int xInitial = selectedPiece.getX();
            int yInitial = selectedPiece.getY();
            Piece pieceTaken = board.getPiece(x, y);
            MoveEntry moveToExecute = new MoveEntry(selectedPiece, xInitial, yInitial,
                                                        pieceTaken, x, y);
            gameLog.push(moveToExecute);
            board.movePiece(selectedPiece, x, y);
            // When this is reached, the player has either:
            // 1) Moved a piece for the first time.
            // 2) Moved a piece, and then moved that same piece back to its original position.
            // If Case 1, then hasMovedPiece changes from false to true.
            // If Case 2, then hasMovedPiece changes from true to false.
            hasMovedPiece = !hasMovedPiece;
            // Update squares attacking for all pieces after move made.
            board.findSquaresAttackingForAllPieces();
            return;
        }

        // Evaluates if:
        // 1) Player has not selected a piece.
        // 2) Player has selected a piece but has not moved it, and
        //    is trying to select a different piece to move.
        // Guaranteed that Square (x,y) is not empty because
        // canSelect() has been called before select().
        selectedPiece = squareOccupant;
        xSelected = x;
        ySelected = y;
        hasSelectedPiece = true;      
    }

    /** End turn by switching control to other player (changing isBlack) and
      * resetting the instance variables selectedPiece, xSelected,
      * ySelected, hasSelectedPiece, and hasMovedPiece.
      */
    public void endTurn() {
        isBlack = !isBlack;
        selectedPiece = null;
        xSelected = -1;
        ySelected = -1;
        hasSelectedPiece = false;
        hasMovedPiece = false;
    }

    /** Check to see if King is in checkmate. 
      * Return tree if King is in checkmate. */
    public boolean isGameOver() {
        return board.isKingInCheckMate(isBlack);
    }

}
