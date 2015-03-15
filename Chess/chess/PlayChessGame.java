package chess;

public class PlayChessGame {

	public static void main(String[] args) {
		Board b = new Board(false);
        int size = 8;
        StdDrawPlus.setXscale(0, size);
        StdDrawPlus.setYscale(0, size);
        int x = 20;
        int y = 20;
        // String won = "Start Game";

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {    		
            b.drawBoard();

            if (StdDrawPlus.mousePressed()) {
                double xDouble = StdDrawPlus.mouseX();
                double yDouble = StdDrawPlus.mouseY();
                x = (int) xDouble;
                y = (int) yDouble;
                
     //            if(b.canSelect(x, y)){
					// b.select(x,y);
     //            }
            }

            // if (b.hasSelectedPiece == true) {
            //     StdDrawPlus.filledSquare(b.chosenX + .5, b.chosenY + .5, .5);
            //     StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            //     b.drawPieceOnBoard(b.chosenX, b.chosenY);
            // }
                
            // if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            // 	b.endTurn(); 

            // 	won = b.winner();
            // 	if( won == null )                 continue;
            // 	if( won.equals("Fire") )          break;
            // 	else if( won.equals("Water") )    break;
            // 	else if( won.equals("No one") )   break;
            // }
            	
            StdDrawPlus.show(100);
        }
	}

}