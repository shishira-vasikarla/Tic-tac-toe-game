package java_projects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tic_tac_toe {
	
	static ArrayList<Integer> player_Positions = new  ArrayList<Integer> ();
	static  ArrayList<Integer> cpu_Positions = new  ArrayList<Integer> ();
	
	public static void main (String[] args) {
		
		char [][] gameBoard = { {' ', '|',' ','|',' '},
				{'-', '+','-','+','-'},
				{' ', '|',' ','|',' '},
				{'-', '+','-','+','-'},
				{' ', '|',' ','|',' '}
		};
		
		//printGameBoard(gameBoard);
		while(true) {
		Scanner place = new Scanner (System.in);
		System.out.println ("Choose a place 1 though 9");
		
		int players_place = place.nextInt();
		
		while(player_Positions.contains(players_place) || cpu_Positions.contains(players_place)) {
			System.out.println("Position is taken! Make a new choice");
			players_place = place.nextInt();
		}
		//int position = place.nextInt();
		
		//System.out.println(position);
		
		 place_the_char (gameBoard, players_place,"player"); 
		 
		 String result = checkWinner();
		 if(result.length() >0) {
			 printGameBoard(gameBoard); 
			 System.out.println(result); 
			 break;
		 }
		 // randomly generating the positions for the cpu side of play.
		 Random rand = new Random();
		 int cpu_place = rand.nextInt(9) + 1;
		 
		 while(cpu_Positions.contains(cpu_place) || cpu_Positions.contains(cpu_place)) {
				//System.out.println("Position is taken! Make a new choice");
				cpu_place = rand.nextInt(9) + 1;
			}
		 place_the_char (gameBoard, cpu_place,"cpu");
		 
		 
		 printGameBoard(gameBoard);
		 
		 result = checkWinner();
		 if(result.length() >0) {
			 System.out.println(result); 
			 printGameBoard(gameBoard); 
			 break;
		 }
		
		
		}
		
	}	
		
		
		public static void printGameBoard(char [][]gameBoard) {
			
		
		// creating a loop to print each row inside gameBoard array
		for (char [] row : gameBoard) {
			// creating a loop to print each character inside each row of the gameBoard.
			for (char r : row) {
				System.out.print(r);
			}
			System.out.println();
			
		}
		
			}
	public static void place_the_char (char [][] gameBoard, int position, String user) {
		
		char symbol = 'X';
		
		if (user == "player") {
			symbol = 'X';
			player_Positions.add(position);
			
		}else if ( user == "cpu"){
			symbol = 'O';
			cpu_Positions.add(position);
		}
		
		switch(position) {
		case 1 :
			gameBoard[0][0] = symbol;
			break;
		case 2 :
			gameBoard[0][2] = symbol;
			break;
		case 3 :
			gameBoard[0][4] = symbol;
			break;
		case 4 :
			gameBoard[2][0] = symbol; // we are leaving row 1 as they are just the symbols. 
			break;                 // from row 2 the blanks begin to enter the characters.
		case 5 :
			gameBoard[2][2] = symbol;
			break;
		case 6 :
			gameBoard[2][4] = symbol;
			break;
		case 7 :
			gameBoard[4][0] = symbol;
			break;
		case 8 :
			gameBoard[4][2] = symbol;
			break;
		case 9 :
			gameBoard[4][4] = symbol;
		break;
		default:
			break;
			
			
		}

	}
	public static String checkWinner () {
		
		List top_row = Arrays.asList(1,2,3);
		List mid_row = Arrays.asList(4,5,6);
		List bot_row = Arrays.asList(7,8,9);
		List left_col = Arrays.asList(1,4,7);
		List mid_col = Arrays.asList(2,5,8);
		List right_col = Arrays.asList(3,6,9);
		List cross_1 = Arrays.asList(1,5,9);
		List cross_2 = Arrays.asList(7,5,3);
		
		//creating list pf lists named winning conditions
		List<List>winningConditions = new ArrayList<List>();
		winningConditions.add(top_row);
		winningConditions.add(mid_row);
		winningConditions.add(bot_row);
		winningConditions.add(left_col);
		winningConditions.add(mid_col);
		winningConditions.add(right_col);
		winningConditions.add(cross_1);
		winningConditions.add(cross_2);
		
		
		for (List l : winningConditions) {
			
			if (player_Positions.containsAll(l)) {
				return "Congratulations you won!";
				
			}else if (cpu_Positions.containsAll(l)) {
				return "CPU wins! Sorry :( ";
				
			}else if (player_Positions.size() + cpu_Positions.size() == 9) {
				return "UH-OH!, No winner!";
			}
		}
		
		return "";
		
	}
		

}
