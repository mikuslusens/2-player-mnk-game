package mnkgame;

import java.io.IOException;
import java.util.Scanner;

public class Game {
	
	// the 3 main variables of the game
	private int m; // amount of fields on the x axis of board
	private int n; // amount of fields on the y axis of board
	private int k; // amount of fields that player needs to fill in order to win
	
	private String winner;
	private int player;
	private int moveCount = 1;
	private boolean draw = false;
	private int[][] matrix;
	
	public void clearConsole() throws InterruptedException, IOException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	
	public void drawBoard() {
		System.out.println("Welcome to an m,n,k game, a.k.a. Tic Tac Toe on steroids! Winner needs to fill "+k+" fields in a line or diagonally.");
		
		System.out.println();
		
		// determine player
		if (winner == null) {
			determinePlayer(moveCount);
			
			if (player == 2)
				System.out.println("It's player X's turn.");
			else
				System.out.println("It's player O's turn.");
			
			System.out.println();
		}
		
		
		System.out.print("    ");
		for(int i = 0; i < m; i++) {System.out.printf(" %3d", i + 1);}
		System.out.println();
		System.out.print("    +");
		for(int i = 0; i < m; i++) {System.out.print("---+");}
		System.out.println();
		
		for(int i = 0; i < n; i++){
			System.out.printf("%3d |", i + 1);
            for(int j = 0; j < m; j++) {
                if(matrix[j][i] == 0) 
            		System.out.printf("   |");
                else if(matrix[j][i] == 1)
                	System.out.printf(" O |");
                else if(matrix[j][i] == 2)
                	System.out.printf(" X |");	
            }
            System.out.println();
            System.out.print("    +");
            for(int l = 0; l < m; l++) {System.out.print("---+");}
            System.out.println();
            
        }
		System.out.println();
	}
	
	public void determinePlayer(int moveCount) {
		if((moveCount & 1) == 0)
            player = 1;
        else
            player = 2;
	}
	
	public void checkIfWinOrDraw (int x, int y, int player) {
		try {    
    		int line = 0;
            for (int i = y + 1; i < n && matrix[x][i] == player; i++, line++);
            for (int i = y; i >= 0 && matrix[x][i] == player; i--, line++);
            
            if (line >= k) {
                if(player == 2)
                	winner = "X";
                else
                	winner = "O";
            }
            line = 0;
            for (int i = x + 1; i < m && matrix[i][y] == player; i++, line++);
            for (int i = x; i >= 0 && matrix[i][y] == player; i--, line++);
            if (line >= k) {
            	if(player == 2)
                	winner = "X";
                else
                	winner = "O";
            }
            line = 0;
            for (int i = x + 1, j = y + 1; i < m && j < n && matrix[i][j] == player; i++, j++, line++);
            for (int i = x, j = y; i >= 0 && j >= 0 && matrix[i][j] == player; i--, j--, line++);
            if (line >= k) {
            	if(player == 2)
                	winner = "X";
                else
                	winner = "O";
            }
            line = 0;
            for (int i = x - 1, j = y + 1; i < m && j >= 0 && matrix[i][j] == player; i--, j++, line++);
            for (int i = x, j = y; i >= 0 && j < n && matrix[i][j] == player; i++, j--, line++);
            if (line >= k) {
            	if(player == 2)
                	winner = "X";
                else
                	winner = "O";
            }
            if(moveCount == (m*n)) {
            	draw = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        	// do nothing - i know this is smelly code, but i couldn't figure out a way to avoid this exception
        }
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		Game game = new Game();
		
		Scanner sc = new Scanner(System.in);
		
		game.clearConsole();
		
		System.out.println("Welcome to an m,n,k game, a.k.a. Tic Tac Toe on steroids! The game board will consist of 'm by n' (m is x axis and n is y axis) fields and the winner must fill 'k' amount of fields in a line or diagonally to win.");
		System.out.println("Please input the m,n,k values.");
		System.out.println();
		System.out.println("WARNING! Because this is a command line game, to avoid clipping issues, you will need to set CMD's (or terminal's) horizontal screen buffer size to AT LEAST m times 5 and vertical screen buffer size to at least n times 4. I higly recommend you test out beforehand how many horizontal lines your monitor can support.");
		System.out.println();
		
		// get m, n, k values 
		while (game.m == 0 || game.n == 0 || game.k == 0) {
			while(game.m == 0 || game.m < 1) {
				System.out.print("Please enter m value: ");
				while (!sc.hasNextInt()) {
			        System.out.println("That's not a number!");
			        System.out.print("Please enter n value: ");
			        sc.next();
				}
				game.m = sc.nextInt();
				if (game.m < 1)
					System.out.println("The value cannot be less than 1!");
			}
			while(game.n == 0 || game.n < 1) {
				System.out.print("Please enter n value: ");
				while (!sc.hasNextInt()) {
			        System.out.println("That's not a number!");
			        System.out.print("Please enter n value: ");
			        sc.next();
				}
				game.n = sc.nextInt();
				if (game.n < 1)
					System.out.println("The value cannot be less than 1!");
			}
			while(game.k == 0) {
				if (game.m < game.n) {
					System.out.print("Please enter k value: ");
					while (!sc.hasNextInt()) {
				        System.out.println("That's not a number!");
				        System.out.print("Please enter k value: ");
				        sc.next();
					}
					game.k = sc.nextInt();
					if (game.k > game.m) {
						System.out.println("m is the smallest of the two, so k cannot be larger than it!");
						game.k = 0;
					}
				} else if (game.m >= game.n) {
					System.out.print("Please enter k value: ");
					while (!sc.hasNextInt()) {
				        System.out.println("That's not a number!");
				        System.out.print("Please enter k value: ");
				        sc.next();
					}
					game.k = sc.nextInt();
					if (game.k > game.n && game.m != game.n) {
						System.out.println("n is the smallest of the two, so k cannot be larger than it!");
						game.k = 0;
					} else if (game.m == game.n && game.k > game.n ) {
						System.out.println("m and n are equal, so k cannot be larger than either of them!");
						game.k = 0;
					}			
				}
			}
		}
		
		game.matrix = new int[game.m][game.n];
		
		while(game.winner == null) {
			
			Integer x = null;
			Integer y = null;
			
			game.clearConsole();
			game.drawBoard();
			
			// get x and y coordinate
			while(x == null || y == null) {
				
				while(x == null || (x < 1 || x > game.m)) {
					System.out.print("Please enter x coordinate (1-"+game.m+"): ");
					while (!sc.hasNextInt()) {
				        System.out.println("That's not a number!");
				        System.out.print("Please enter x coordinate (1-"+game.m+"): ");
				        sc.next();
					}
					x = sc.nextInt();
					if (x < 1)
						System.out.println("The coordinate cannot be less than 1!");
					else if(x > game.m)
						System.out.println("The coordinate cannot be greater than "+game.m+"!");
				}
				
				while(y == null || (y < 1 || y > game.n) ) {
					System.out.print("Please enter y coordinate (1-"+game.n+"): ");
					while (!sc.hasNextInt()) {
				        System.out.println("That's not a number!");
				        System.out.print("Please enter y coordinate (1-"+game.n+"): ");
				        sc.next();
					}
					y = sc.nextInt();
					if (y < 1)
						System.out.println("The coordinate cannot be less than 1!");
					else if(y > game.n)
						System.out.println("The coordinate cannot be greater than "+game.n+"!");
				}
								
				if (game.matrix[x-1][y-1] != 0) {
					System.out.println("Selected field is already selected!");
					x = null;
					y = null;
				}
			}
			x = x - 1;
			y = y - 1;
			
			game.matrix[x][y] = game.player;
			
			game.checkIfWinOrDraw(x, y, game.player);
			
			if(game.draw == true)
				break;
			
			game.moveCount++;
			
		}
		
		sc.close();
		
		game.clearConsole();
		
		game.drawBoard();
		
		if (game.winner != null)
			System.out.println("Game over, player " + game.winner + " wins!");
		if (game.draw == true)
			System.out.println("Game over, it's a draw!");		

	}

}
