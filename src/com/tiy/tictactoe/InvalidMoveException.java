package com.tiy.tictactoe;

/**
 * Created by dbashizi on 12/9/16.
 */
public class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }
}
