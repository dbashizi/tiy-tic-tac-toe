package com.tiy.tictactoe;

import java.util.Scanner;

/**
 * Created by dbashizi on 12/9/16.
 */
public class GameRunner {
    TicTacToeGame game;

    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("Welcome to Tic Tac Toe");
        new GameRunner().startUltimateGame();
    }

    private void startUltimateGame() throws InvalidMoveException {
        System.out.println("Let's start the game!");

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What's player 1's name?");
        String userInput = inputScanner.nextLine();
        Player player1 = new Player(PlayerIdentity.PLAYER1, userInput);

        System.out.println("What's player 2's name?");
        userInput = inputScanner.nextLine();
        Player player2 = new Player(PlayerIdentity.PLAYER2, userInput);

        System.out.println("On each turn, you will indicate which square you want to play in ...");
        System.out.println("The squares are numbered as displayed below: ");
        TicTacToeGame rulesGame = new TicTacToeGame(true);
        rulesGame.printBoard();

        while (true) {
            TicTacToeUltimateGame ultimateGame = new TicTacToeUltimateGame();
            while (true) { // replace with checking if ultimate game is over
                TicTacToeGame game = ultimateGame.getActiveGame();
                if (game == null) {
                    System.out.println("Please choose a board to play on (enter 0 to display the ultimate board");
                    int boardChoice = Integer.parseInt(inputScanner.nextLine());
                    if (boardChoice == 0) {
                        ultimateGame.printBoard();
                        boardChoice = Integer.parseInt(inputScanner.nextLine());
                    }
                    game = ultimateGame.getSpecificGame(boardChoice);
                }

                makeUltimateMove(ultimateGame, game, player1, inputScanner, rulesGame);

                game = ultimateGame.getActiveGame();
                if (game == null) {
                    System.out.println("Please choose a board to play on (enter 0 to display the ultimate board");
                    int boardChoice = Integer.parseInt(inputScanner.nextLine());
                    if (boardChoice == 0) {
                        ultimateGame.printBoard();
                        boardChoice = Integer.parseInt(inputScanner.nextLine());
                    }
                    game = ultimateGame.getSpecificGame(boardChoice);
                }
                makeUltimateMove(ultimateGame, game, player2, inputScanner, rulesGame);

                if (false) {
                    break;
                }
            }

            if (game.getWinner() != null) {
                System.out.println(game.getWinner().getName() + " wins!");
            } else {
                System.out.println("It's a draw!");
            }
            System.out.println("Game over!");
            System.out.println();
            System.out.println("Would you like to play again? (y/n)");
            userInput = inputScanner.nextLine();
            if (userInput.equalsIgnoreCase("n")) {
                break;
            }
        }

        System.out.println("Thank you for playing!");

    }

    private void startGame() throws InvalidMoveException {
        System.out.println("Let's start the game!");

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What's player 1's name?");
        String userInput = inputScanner.nextLine();
        Player player1 = new Player(PlayerIdentity.PLAYER1, userInput);

        System.out.println("What's player 2's name?");
        userInput = inputScanner.nextLine();
        Player player2 = new Player(PlayerIdentity.PLAYER2, userInput);

        System.out.println("On each turn, you will indicate which square you want to play in ...");
        System.out.println("The squares are numbered as displayed below: ");
        TicTacToeGame rulesGame = new TicTacToeGame(true);
        rulesGame.printBoard();

        while (true) {
            TicTacToeGame game = new TicTacToeGame();
            while (!game.isGameOver()) {
                makeMove(game, player1, inputScanner, rulesGame);
                if (!game.isGameOver()) {
                    makeMove(game, player2, inputScanner, rulesGame);
                }
            }

            if (game.getWinner() != null) {
                System.out.println(game.getWinner().getName() + " wins!");
            } else {
                System.out.println("It's a draw!");
            }
            System.out.println("Game over!");
            System.out.println();
            System.out.println("Would you like to play again? (y/n)");
            userInput = inputScanner.nextLine();
            if (userInput.equalsIgnoreCase("n")) {
                break;
            }
        }

        System.out.println("Thank you for playing!");
    }

    private void makeUltimateMove(TicTacToeUltimateGame ultimateGame, TicTacToeGame game, Player player,
                          Scanner inputScanner,
                          TicTacToeGame rulesGame) throws InvalidMoveException {
        System.out.println(player.getName() + ": make your move (enter 0 to display the square labels)");
        int moveSquare = Integer.parseInt(inputScanner.nextLine());
        if (moveSquare == 0) {
            rulesGame.printBoard();
            System.out.println("Enter your move: ");
            moveSquare = Integer.parseInt(inputScanner.nextLine());
        }
        ultimateGame.makeMove(player, game,
                game.getRowIndex(moveSquare),
                game.getColIndex(moveSquare));
    }

    private void makeMove(TicTacToeGame game, Player player,
                          Scanner inputScanner,
                          TicTacToeGame rulesGame) throws InvalidMoveException {
        System.out.println(player.getName() + ": make your move (enter 0 to display the square labels)");
        int moveSquare = Integer.parseInt(inputScanner.nextLine());
        if (moveSquare == 0) {
            rulesGame.printBoard();
            System.out.println("Enter your move: ");
            moveSquare = Integer.parseInt(inputScanner.nextLine());
        }
        game.makeMove(player,
                game.getRowIndex(moveSquare),
                game.getColIndex(moveSquare));
    }
}
