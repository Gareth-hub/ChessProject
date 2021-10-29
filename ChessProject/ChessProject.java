import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/
 
public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int whoMove;
	int initialY;
	JPanel panels;
	JLabel pieces;
	
 
    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);
 
        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
 
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
 
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }
 
        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){			
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){			
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);		
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}

	private Boolean checkArea(int x, int y){
		Component c1 = chessBoard.findComponentAt(x-75, y-75);
		Component c2 = chessBoard.findComponentAt(x, y-75);
		Component c3 = chessBoard.findComponentAt(x+75, y-75);
		Component c4 = chessBoard.findComponentAt(x-75, y);
		Component c5 = chessBoard.findComponentAt(x+75, y);
		Component c6 = chessBoard.findComponentAt(x-75, y+75);
		Component c7 = chessBoard.findComponentAt(x, y+75);
		Component c8 = chessBoard.findComponentAt(x+75, y+75);
		if((c1 instanceof JPanel)&&(c2 instanceof JPanel)&&(c3 instanceof JPanel)&&(c4 instanceof JPanel)
				&&(c5 instanceof JPanel)&&(c6 instanceof JPanel)&&(c7 instanceof JPanel)&&(c8 instanceof JPanel)){
			return false;
		}
		else {
			int counter = 0;
			// For loop for checking each surrounding square
			for(int i = counter; i < 8; i++){
				if(counter == 0){
					if((c1 instanceof JPanel) == false){
						JLabel check1 = (JLabel)c1;
						String tmp1 = check1.getIcon().toString();
						if(tmp1.contains("King")){
							return true;
						}
						else{
							counter ++;
						}
					}
					else{
						counter ++;
					}
				}
				else if(counter == 1){
					if((c2 instanceof JPanel) == false){
						JLabel check1 = (JLabel)c2;
						String tmp1 = check1.getIcon().toString();
						if(tmp1.contains("King")){
							return true;
						}
						else{
							counter ++;
						}
					}
					else{
						counter ++;
					}
				}
				else if(counter == 2){
					if((c3 instanceof JPanel) == false){
						JLabel check1 = (JLabel)c3;
						String tmp1 = check1.getIcon().toString();
						if(tmp1.contains("King")){
							return true;
						}
						else{
							counter ++;
						}
					}
					else{
						counter ++;
					}
				}else if(counter == 3){
					if((c4 instanceof JPanel) == false){
						JLabel check1 = (JLabel)c4;
						String tmp1 = check1.getIcon().toString();
						if(tmp1.contains("King")){
							return true;
						}
						else{
							counter ++;
						}
					}
					else{
						counter ++;
					}
				}else if(counter == 4){
					if((c5 instanceof JPanel) == false){
						JLabel check1 = (JLabel)c5;
						String tmp1 = check1.getIcon().toString();
						if(tmp1.contains("King")){
							return true;
						}
						else{
							counter ++;
						}
					}
					else{
						counter ++;
					}
				}else if(counter == 5){
					if((c6 instanceof JPanel) == false){
						JLabel check1 = (JLabel)c6;
						String tmp1 = check1.getIcon().toString();
						if(tmp1.contains("King")){
							return true;
						}
						else{
							counter ++;
						}
					}
					else{
						counter ++;
					}
				}else if(counter == 6){
					if((c7 instanceof JPanel) == false){
						JLabel check1 = (JLabel)c7;
						String tmp1 = check1.getIcon().toString();
						if(tmp1.contains("King")){
							return true;
						}
						else{
							counter ++;
						}
					}
					else{
						counter ++;
					}
				}
				else if(counter == 7){
					if((c8 instanceof JPanel) == false){
						JLabel check1 = (JLabel)c8;
						String tmp1 = check1.getIcon().toString();
						if(tmp1.contains("King")){
							return true;
						}
						else{
							counter ++;
						}
					}
					else{
						counter ++;
					}
				}
				else{
					return false;
				}
			}
		}
		return false;
	}



	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("Black")))){
			oponent = true;
		}
		else{
			oponent = false; 
		}		
		return oponent;
	}

	private Boolean checkBlackOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("White")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}


	private Boolean checkBlackKing(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("BlackKing")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}

	private Boolean checkWhiteKing(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("WhiteKing")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}



	/*
		This method is called when we press the Mouse. So we need to find out what piece we have 
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel) 
			return;
 
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }
   
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }
     
 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before 
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
		if (chessPiece == null) return;

		chessPiece.setVisible(false);
		Boolean success = false;
		Boolean progression = false;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length() - 4));
		Boolean validMove = false;

		int landingX = (e.getX() / 75);
		int landingY = (e.getY() / 75);
		int xMovement = Math.abs((e.getX() / 75 - startX));
		int yMovement = Math.abs((e.getY() / 75 - startY));
		System.out.println("-----------------------------------------------");
		System.out.println("The piece being moved is :" + pieceName);
		System.out.println("The starting coordinates are:" + "(" + startX + "," + startY + ")");
		System.out.println("The xMovement is : " + xMovement);
		System.out.println("The yMovement is : " + yMovement);
		System.out.println("The landing coordinates are:" + "(" + landingX + "," + landingY + ")");
		System.out.println("------------------------------------------------");

		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.
			
			So a Pawn is able to move two squares forward one its first go but only one square after that. 
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing 
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one 
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position. 
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for 
			demonstration purposes the Pawn here turns into a Queen.
		*/

		// "whoMove" is the variable that determines whos move it is in the game,
		// If it's equal to zero (0) that means it is White's turn & If its equal to one (1) then it is Black's turn
		// However if whoMove is neither equal to 0 (for White movement) or 1 (for Black movement) then no pieces can move

		if(whoMove==0){
			System.out.println("White has just moved " + pieceName);
		}

		else if(whoMove==1){
			System.out.println("Black has just moved " + pieceName);
		}

		//Code for piece movement

		// BISHOP Code
		 if (pieceName.contains("Bishop")) {
			Boolean blockingWay = false; //Checks if the landing square is occupied by another piece
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			}
			//The code above makes sure that for the move to be valid the bishop must be put back on the board
			//The code below controls the bishops movement and ensures that they can only move diagonally
			else {
				if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
					if ((startX - landingX < 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
								blockingWay = true;
							}
						}
					} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
								blockingWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
								blockingWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
								blockingWay = true;
							}
						}
					}
					if (blockingWay) {
						validMove = false;
					} else {
						//We use this code to check if there is a piece present in the square we want to move our piece to
						// & if we can take a piece that is in the way
						if (piecePresent(e.getX(), (e.getY()))) {
							if (checkBlackKing(e.getX(), e.getY())) { //Checks if the Black King is present
								System.out.println("Black has won the game ^_^ ! Unlucky White :p");
								System.exit(0); // If the Black King is killed by a white Bishop the game is over
							}
							//This controls whites attack movement and stops white pieces from taking each other
							if (pieceName.contains("White")) {
								if (checkWhiteOponent(e.getX(), e.getY())) {
									//This if statement below is used to stop the pieces once they have made their move
									//For example, without this code after the White bishop killed a pawn,
									// it could freely kill another black piece even though it would be blacks turn to move
									if(whoMove==0) {
										validMove = true;
										whoMove++;
									}
									//If it is not white's turn then any movement is false
									else {
										if (whoMove == 1) {
											validMove = false;
										}
									}
								} else {
									validMove = false;
								}
							} else {
								if (checkWhiteKing(e.getX(), e.getY())) { //Checks if the enemy piece is the White King
									System.out.println("Black has won the game ^_^ ! Unlucky White :p ");
									System.exit(0);// This ends the game after the King is killed
								}
								if (checkBlackOponent(e.getX(), e.getY())) {
									if(whoMove==1) { //Ensures that to kill an enemy that it must be Black's turn
										validMove = true; // & that after Black has had a sucessful turn it cant move again until white has moved
										whoMove--;
									}
									else {
										if (whoMove == 0) {
											validMove = false;
										}
									}
								}
								else {
									validMove = false;
								}
							}
						}
						else{
							if(pieceName.contains("White")){ //Controls when the white Bishop can move
								if(whoMove==0){ // Whites turn to move
									validMove = true;
									whoMove++;
								}

								else if(whoMove==1){ //Stops White from moving if its Black's turn
									validMove = false;
								}
								 //For if the move count is not equal to 0 (for white) or 1 (for black)
								//Then no moves can be made by either piece
									else{
									validMove = false;
								}
							}
							else if(pieceName.contains("Black")){ //For Black Bishops movement
								if(whoMove==0){ //Prevents the bishop from moving until white has moved first and set the move counter to (1)
									validMove = false;
								}
								else if(whoMove==1){ //Movecounter is equal to 1 so Black Bishop movement is valid
									validMove = true;
									whoMove--;
								}
								//For if the move count is not equal to 0 (for white) or 1 (for black)
								//Then no moves can be made by either colour
								else{
									validMove = false;
								}
							}
							else{
								validMove = false;
							}
						}
					}
				}
				else{
					validMove = false; //If the required conditions are not met then no movements can be made
				}
			}
		}

		 //QUEEN CODE
		if (pieceName.contains("Queen")) {
			Boolean blockingWay = false;
			int distance = Math.abs(startX - landingX);
			//This code makes sure the Queen must be placed on the board and cannot be placed outside the board
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			}
			//Queen Movement, we create the queen movement by combining the Rook and Bishop movement
			else {
				//This code controls the Queen's diagonal movements, the queen follows the same diagnol movement as the bishop
				if (Math.abs(startX - landingX) == Math.abs(startY - landingY) || ((Math.abs(startX - landingX) != 0)
						&& (Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
					if ((startX - landingX < 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
								blockingWay = true;
							}
						}
					} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
								blockingWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
								blockingWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
								blockingWay = true;
							}
						}
					}
					//This controls the Queen's linear movements, we do this by implementing the rook movement below
					else {
						if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0))
								|| ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
							if (Math.abs(startX - landingX) != 0) {
								if (startX - landingX > 0) {
									for (int i = 0; i < xMovement; i++) {
										if (piecePresent(initialX - (i * 75), e.getY())) {
											blockingWay = true;
											break;
										} else {
											blockingWay = false;
										}
									}
								} else {
									for (int i = 0; i < xMovement; i++) {
										if (piecePresent(initialX + (i * 75), e.getY())) {
											blockingWay = true;
											break;
										} else {
											blockingWay = false;
										}
									}
								}
							} else {
								if (startY - landingY > 0) {
									for (int i = 0; i < yMovement; i++) {
										if (piecePresent(e.getX(), initialY - (i * 75))) {
											blockingWay = true;
											break;
										} else {
											blockingWay = false;
										}
									}
								}
								else {
									for (int i = 0; i < yMovement; i++) {
										if (piecePresent(e.getX(), initialY + (i * 75))) {
											blockingWay = true;
											break;
										} else {
											blockingWay = false;
										}
									}
								}
							}
						}
					}
					if (blockingWay) {
						validMove = false;
					} else {
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("White")) {
								if (checkBlackKing(e.getX(), e.getY())) {
									System.out.println("Black has won the game ^_^ ! Unlucky White :p ");
									System.exit(0); //End the game when the King is killed

								}
								if (checkWhiteOponent(e.getX(), e.getY())) {
									if(whoMove==0) {
										validMove = true;
										whoMove++;
									}
									else {
										if (whoMove == 1) {
											validMove = false;
										}
									}
								} else {
									validMove = false;
								}
							} else {
								if (checkWhiteKing(e.getX(), e.getY())) {
									System.out.println("White has won the game ^_^ ! Unlucky Black :p ");
									System.exit(0);
								}
								if (checkBlackOponent(e.getX(), e.getY())) {
									if(whoMove==1) {
										validMove = true;
										whoMove--;
									}
									else {
										if (whoMove == 0) {
											validMove = false;
										}
									}
								} else {
									validMove = false;
								}
							}
						}
						else{
							if(pieceName.contains("White")){
								//White Move
								if(whoMove==0){
									validMove = true;
									whoMove++;
								}

								else if(whoMove==1){
									validMove = false;
								}
								//For if the move count is not equal to 0 (for white) or 1 (for black)
								//Then no moves can be made by either piece
								else{
									validMove = false;
								}
							}
							else if(pieceName.contains("Black")){ //For Black Queen movement
								if(whoMove==0){ //Prevents the Queen from moving until white has moved first and set the move counter to (1)
									validMove = false;
								}
								else if(whoMove==1){ //Movecounter is equal to 1 so Black Queen movement is valid
									validMove = true;
									whoMove--;
								}

								else{
									validMove = false;
								}
							}
							else{
								validMove = false;
							}
						}
					}
				}
				else{
					validMove = false; //If none of the required conditions are met then the queen cannot move
				}
			}
		}

		//ROOK Movement
		if (pieceName.contains("Rook")) {
			Boolean blockingWay = false;
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || landingY > 7)) {
				validMove = false;
			} else {
				if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0))
						|| ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
					if (Math.abs(startX - landingX) != 0) {
						if (startX - landingX > 0) {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX - (i * 75), e.getY())) {
									blockingWay = true;
									break;
								} else {
									blockingWay = false;
								}
							}
						} else {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX + (i * 75), e.getY())) {
									blockingWay = true;
									break;
								} else {
									blockingWay = false;
								}
							}
						}
					} else {
						if (startY - landingY > 0) {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY - (i * 75))) {
									blockingWay = true;
									break;
								} else {
									blockingWay = false;
								}
							}
						}/*checking*/ else {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY + (i * 75))) {
									blockingWay = true;
									break;
								} else {
									blockingWay = false;
								}
							}
						}
					}
					if (blockingWay) {
						validMove = false;
					} else {
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("White")) {
								if (checkBlackKing(e.getX(), e.getY())) {
									System.out.println("White has won the game ^_^ ! Unlucky Black :p ");
									System.exit(0);
								}
								if (checkWhiteOponent(e.getX(), e.getY())) {
									if(whoMove==0) {
										validMove = true;
										whoMove++;
									}
									else {
										if (whoMove == 1) {
											validMove = false;
										}
									}
								} else {
									validMove = false;
								}
							} else {
								if (checkWhiteKing(e.getX(), e.getY())) {
									System.out.println("Black has won the game ^_^ ! Unlucky White :p ");
									System.exit(0);
								}
								if (checkBlackOponent(e.getX(), e.getY())) {
									if(whoMove==1) {
										validMove = true;
										whoMove--;
									}
									else {
										if (whoMove == 0) {
											validMove = false;
										}
									}
								} else {
									validMove = false;
								}
							}
						}
						else{
							if(pieceName.contains("White")){
								//White Move
								if(whoMove==0){
									validMove = true;
									whoMove++;
								}

								else if(whoMove==1){
									validMove = false;
								}
								//For if the move count is not equal to 0 (for white) or 1 (for black)
								//Then no moves can be made by either piece

							}
							else if(pieceName.contains("Black")){ //For Black Rook's movement
								if(whoMove==0){ //Prevents the Rook from moving until white has moved first and set the move counter to (1)
									validMove = false;
								}
								else if(whoMove==1){ //Movecounter is equal to 1 so Black Rook movement is valid
									validMove = true;
									whoMove--;
								}
							}
							else{
								validMove = false;
							}
						}

					}
				}
				else{
					validMove = false; //If the Rook doesn't meet the above conditions then it cannot move
				}
			}
		}

		//KNIGHT Movement
		if (pieceName.contains("Knight")) {
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || landingY > 7)) {
				validMove = false;
			} else {
				//Controls the Knight Movement and creates the L shaped movement pattern
				if (((landingX == startX + 1) && (landingY == startY + 2)) || ((landingX == startX - 1) && (landingY == startY + 2)) || ((landingX == startX + 2) && (landingY == startY + 1)) || ((landingX == startX - 2) && (landingY == startY + 1))
						|| ((landingX == startX + 1) && (landingY == startY - 2)) || ((landingX == startX - 1) && (landingY == startY - 2))
						|| ((landingX == startX + 2) && (landingY == startY - 1)) || ((landingX == startX - 2) && (landingY == startY - 1))) {
					if (piecePresent(e.getX(), e.getY())) {
						if (pieceName.contains("White")) {
							if (checkBlackKing(e.getX(), e.getY())) {
								System.out.println("White has won the game ^_^ ! Unlucky Black :p ");
								System.exit(0);
							}
							if (checkWhiteOponent(e.getX(), e.getY())) {
								if(whoMove==0) {
									validMove = true;
									whoMove++;
								}
								else {
									if (whoMove == 1) {
										validMove = false;
									}
								}
							} else {
								validMove = false;
							}
						} else {
							if (checkWhiteKing(e.getX(), e.getY())) {
								System.out.println("Black has won the game ^_^ ! Unlucky White :p ");
								System.exit(0);
							}
							if (checkBlackOponent(e.getX(), e.getY())) {
								if(whoMove==1) {
									validMove = true;
									whoMove--;
								}
								else {
									if (whoMove == 0) {
										validMove = false;
									}
								}
							} else {
								validMove = false;
							}
						}
					} else{
						if(pieceName.contains("White")){
							if(whoMove==0){ // Controls White movement & ensures that the white Knight can only move when its White's turn
								validMove = true;
								whoMove++;
							}

							else if(whoMove==1){ //Stops White Knight from moving when it's Black's turn
								validMove = false;
							}
							//For if the move count is not equal to 0 (for white) or 1 (for black)
							//Then no moves can be made by either piece
							else{
								validMove=false;
							}
						}
						else if(pieceName.contains("Black")){
							if(whoMove==0){
								validMove = false;
							}
							else if(whoMove==1){
								validMove = true;
								whoMove--;
							}
							else{
								validMove=false;
							}
						}
						else{
							validMove = false;
						}
					}
				}
				else{
				validMove = false;
			}
		}
	}

		//BLACK PAWN Movement
		if (pieceName.equals("BlackPawn")) {
			if (whoMove == 0) {
				validMove = false;
			} else if (whoMove == 1) {
			if ((startY == 6) && (startX == landingX) && (((startY - landingY) == 1) || (startY - landingY) == 2)) {
				if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), (e.getY() + 75)))) {
					validMove = true;
					whoMove--;
					if (landingY == 0) {
						progression = true;
					}
				} else {
					validMove = false;
				}
			} else if ((startY != 6) && ((startX == landingX) && (((startY - landingY) == 1)))) {
				// if there is not a piece in the way
				if (!piecePresent(e.getX(), e.getY())) {
					if (landingY == 0) {
						progression = true;
						validMove = true;
						whoMove --;
					} else {
						validMove = true;
						whoMove --;
					}
				} else {
					validMove = false;
				}
			} else if ((Math.abs(startX - landingX) == 1) && (((startY - landingY) == 1))) {
				if (piecePresent(e.getX(), e.getY())) {
					if (checkWhiteKing(e.getX(), e.getY())) {
						System.out.println("Black has won the game ^_^ ! Unlucky White :p ");
						System.exit(0);
					}
					if (checkBlackOponent(e.getX(), e.getY())) {
						if (landingY == 0) {
							validMove = true;
							progression = true;
							whoMove --;
						}
						else {
							validMove = true;
							whoMove--;
						}
					} else {
						validMove = false;
					}
				}
				else {
					validMove = false;
				}
			}
			else{
				validMove=false;
			}
		}
	}

		//KING Movement
		if (pieceName.contains("King")) {
			Boolean blockingWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			} else {
				//This is the code for the King's movement
				if ((Math.abs(startX - landingX) == 1) && (Math.abs(startY - landingY) == 1)
						|| (((Math.abs(startX - landingX) == 1) && (Math.abs(startY - landingY) == 0))
						|| ((Math.abs(startX - landingX) == 0) && (Math.abs(startY - landingY) == 1)))) {

					//Diagonal Movements
					if ((startX - landingX < 0) && (startY - landingY < 0)) { //downright
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
								blockingWay=true;
							}
							else{
								blockingWay = false;
							}
						}
					} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
								blockingWay = true;
							}
							else{
								blockingWay = false;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
								blockingWay = true;
							}
							else{
								blockingWay = false;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
								blockingWay=true;
							}
							else{
								blockingWay = false;
							}
						}
					}
					else{
						blockingWay = false;
					}
					//Linear Movements
					if (((Math.abs(startX - landingX) == 1) && (Math.abs(startY - landingY) == 0))
							|| ((Math.abs(startX - landingX) == 0) && (Math.abs(startY - landingY) == 1))) {
						if (Math.abs(startX - landingX) != 0) {
							if (startX - landingX > 0) {
								for (int i = 0; i < xMovement; i++) {
									if (piecePresent(initialX - (i * 75), e.getY())) {
										blockingWay = true;
										break;
									} else {
										blockingWay = false;
									}
								}
							} else {
								for (int i = 0; i < xMovement; i++) {
									if (piecePresent(initialX + (i * 75), e.getY())) {
										blockingWay = true;
										break;
									} else {
										blockingWay = false;
									}
								}
							}
						} else {
							if (startY - landingY > 0) {
								for (int i = 0; i < yMovement; i++) {
									if (piecePresent(e.getX(), initialY - (i * 75))) {
										blockingWay = true;
										break;
									} else {
										blockingWay = false;
									}
								}
							} else {
								for (int i = 0; i < yMovement; i++) {
									if (piecePresent(e.getX(), initialY + (i * 75))) {
										blockingWay = true;
										break;
									} else {
										blockingWay = false;
									}
								}
							}
						}
					}
					if (blockingWay) {
						validMove = false;
					} else {
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("White")) {
								/*if (checkBlackKing(e.getX(), e.getY())) {
									System.out.println("White has won the game ^_^ ! Unlucky Black :p ");
									System.exit(0);
								} */ //The code commented ot above is used to check if the Black king has been killed by the black and
                                // ends the game, however since the kings have been set to avoid each other we don't need this code right now
								if (checkWhiteOponent(e.getX(), e.getY())) {
									if (whoMove == 0) {
										if(checkArea(e.getX(), e.getY())) {
											validMove = false;
										}
										else{
											validMove= true;
											whoMove++;
										}
									} else {
										if (whoMove == 1) {
											validMove = false;
										}
									}
								} else {
									validMove = false;
								}
							} else {
								/*if (checkWhiteKing(e.getX(), e.getY())) {
									System.out.println("Black has won the game ^_^ ! Unlucky White :p ");
									System.exit(0);
								} */ //The code commented ot above is used to check if the white king has been killed by the black and
                                // ends the game, however since the kings have been set to avoid each other we don't need this code right now
								if (checkBlackOponent(e.getX(), e.getY())) {
									if (whoMove == 1) {
										if(checkArea(e.getX(), e.getY())){
											validMove = false;
									}
										else{
											validMove=true;
											whoMove--;
										}
									} else {
										if (whoMove == 0) {
											validMove = false;
										}
									}
								} else {
									validMove = false;
								}
							}
						}
						else{
							if(pieceName.contains("White")){ //Controls when White King can move
								if(whoMove==0) {
									if (checkArea(e.getX(), e.getY())) {
										validMove = false; //If there is an enemy king within 1 square of the landing area in any direction
                                                          // The king is unable to move
									}
									else{
										validMove=true; //If there is no enemy king within 1 square of the landing area in any direction
										whoMove++;      // The king movement is valid
									}
								}

								else if(whoMove==1){
									validMove = false;
								}
								//For if the move count is not equal to 0 (for white) or 1 (for black)
								else{
									validMove=false; //Then no moves can be made by either piece
								}
							}
							else if(pieceName.contains("Black")){ //For Black King movement
								if(whoMove==0){ //Prevents the BlackKing from moving until white has moved first and set the move counter to (1)
									validMove = false;
								}
								else if(whoMove==1) { //Movecounter is equal to 1 so Black King movement is valid
									if (checkArea(e.getX(), e.getY())) {
										validMove = false; //If the landing area contains an enemy king within 1 square then it cannot move
									}
									else{ //If there is not an enemy king surrounding the landing area the King can move
										validMove= true;
										whoMove--;
									}
								}
								else{
									validMove=false;
								}
							}
							else{
								validMove = false;
							}
						}
					}
				}
				else{
					validMove = false; //If the King does not meet the required conditions above it cannot move
				}
			}
		} //End of King Code


		//WHITE PAWN Movement
		if(pieceName.equals("WhitePawn")){
			if(whoMove == 0){ //if the move counter "whoMove" is equal to zero then it is whites turn
				if((startY == 1)&&(startX == landingX)&&(((landingY-startY)== 1)||(landingY-startY)== 2)){

					if((landingY-startY)== 2) {
						if((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), e.getY()-75))) {
							validMove = true;
							whoMove++;
						}
						else{
							validMove = false;
						}
					}
					else if((landingY-startY)== 1){
						if (!piecePresent(e.getX(), e.getY())) {
							validMove = true;
							whoMove++;
						}
						else {
							validMove = false;
						}
					}
				}
				else if((startY != 1)&&((landingX == startX)&&(((landingY-startY)== 1)))){
					if (!piecePresent(e.getX(), e.getY())) { //checks that there's no piece present
						if(landingY == 7){
							validMove = true;
							success = true;
							whoMove++;
						}
						else{
							validMove = true;
							whoMove++;
						}
					}
					else {
						validMove = false;
					}
				}
				else if ((Math.abs(startX - landingX) == 1) && (((landingY - startY) == 1))) {
					if (piecePresent(e.getX(), e.getY())) { //Check for the black king
						if (checkBlackKing(e.getX(), e.getY())){
							System.out.println("White is the winner! Unlucky Black :p");
							System.exit(0);
						}
						if (checkWhiteOponent(e.getX(), e.getY())){
							if(landingY == 7){
								validMove = true;
								success = true;
								whoMove++;
							}
							else{
								validMove = true;
								whoMove++;
							}
						}
						else {
							validMove = false;
						}
					}
					else {
						validMove = false;
					}
				}

				else {
					validMove = false;
				}

			}
			else if(whoMove == 1){
				validMove = false;
			}
		}

		if (!validMove) {
			int location = 0;
			if (startY == 0) {
				location = startX;
			} else {
				location = (startY * 8) + startX;
			}
			String pieceLocation = pieceName + ".png";
			pieces = new JLabel(new ImageIcon(pieceLocation));
			panels = (JPanel) chessBoard.getComponent(location);
			panels.add(pieces);
		} else {
			if (progression) {
				int location = 0 + (e.getX() / 75);
				if (c instanceof JLabel) {
					Container parent = c.getParent();
					parent.remove(0);
					pieces = new JLabel(new ImageIcon("BlackQueen.png"));
					parent = (JPanel) chessBoard.getComponent(location);
					parent.add(pieces);
				}
				else {
					Container parent = (Container) c;
					pieces = new JLabel(new ImageIcon("BlackQueen.png"));
					parent = (JPanel) chessBoard.getComponent(location);
					parent.add(pieces);
				}
			} else {
				if (success) {
					int location = 56 + (e.getX() / 75);
					if (c instanceof JLabel) {
						Container parent = c.getParent();
						parent.remove(0);
						pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
						parent = (JPanel) chessBoard.getComponent(location);
						parent.add(pieces);
					} else {
						Container parent = (Container) c;
						pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
						parent = (JPanel) chessBoard.getComponent(location);
						parent.add(pieces);
					}
				} else {
					if (c instanceof JLabel) {
						Container parent = c.getParent();
						parent.remove(0);
						parent.add(chessPiece);
					} else {
						Container parent = (Container) c;
						parent.add(chessPiece);
					}
					chessPiece.setVisible(true);
				}
			}
		}
	}
 
    public void mouseClicked(MouseEvent e) {
	
    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){
	
    }
    public void mouseExited(MouseEvent e) {
	
    }
 	
	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}


