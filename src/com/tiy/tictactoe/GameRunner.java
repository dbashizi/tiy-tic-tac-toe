package com.tiy.tictactoe;

/**
 * Created by dbashizi on 12/9/16.
 */
public class GameRunner {
    TicTacToeGame game;

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe");
        new GameRunner().startGame();
    }

    private void startGame() {
        System.out.println("Let's start the game!");

    }
}
