package java_projects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tic_tac_toe {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose a position (1 through 9):");

            int playerPosition = getPlayerInput(scanner);

            while (isPositionTaken(playerPosition)) {
                System.out.println("Position is taken! Make a new choice:");
                playerPosition = getPlayerInput(scanner);
            }

            placeChar(gameBoard, playerPosition, "player");
            printGameBoard(gameBoard);

            String result = checkWinner();
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }

            int cpuPosition = getCpuInput();

            while (isPositionTaken(cpuPosition)) {
                cpuPosition = getCpuInput();
            }

            placeChar(gameBoard, cpuPosition, "cpu");
            printGameBoard(gameBoard);

            result = checkWinner();
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }
        }
    }

    public static int getPlayerInput(Scanner scanner) {
        int playerInput;
        while (true) {
            if (scanner.hasNextInt()) {
                playerInput = scanner.nextInt();
                if (playerInput >= 1 && playerInput <= 9) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a number between 1 and 9:");
                }
            } else {
                scanner.next(); // Consume invalid input
                System.out.println("Invalid input! Please enter a number between 1 and 9:");
            }
        }
        return playerInput;
    }

    public static int getCpuInput() {
        Random rand = new Random();
        return rand.nextInt(9) + 1;
    }

    public static boolean isPositionTaken(int position) {
        return playerPositions.contains(position) || cpuPositions.contains(position);
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char r : row) {
                System.out.print(r);
            }
            System.out.println();
        }
    }

    public static void placeChar(char[][] gameBoard, int position, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(7, 5, 3);

        List<List<Integer>> winningConditions = new ArrayList<List<Integer>>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for (List<Integer> condition : winningConditions) {
            if (playerPositions.containsAll(condition)) {
                return "Congratulations! You won!";
            } else if (cpuPositions.containsAll(condition)) {
                return "CPU wins! Sorry :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "It's a tie! No winner.";
            }
        }

        return "";
    }
}
