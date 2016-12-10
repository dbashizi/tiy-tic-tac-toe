package com.tiy.tictactoe;

/**
 * Created by dbashizi on 12/9/16.
 */
public enum PlayerIdentity {
    PLAYER1('X'),
    PLAYER2('O');

    private char symbol;

    PlayerIdentity(char symbol) {
        this.symbol = symbol;
    }

    char symbol() {
        return symbol;
    }
}
