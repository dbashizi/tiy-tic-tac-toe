package com.tiy.tictactoe;

/**
 * Created by dbashizi on 12/9/16.
 */
public class Player {
    private PlayerIdentity identity;
    private String name;

    public Player(PlayerIdentity identity, String name) {
        this.identity = identity;
        this.name = name;
    }

    public PlayerIdentity getIdentity() {
        return identity;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        Player otherPlayer = (Player)otherObject;
        if (otherPlayer.getIdentity() == identity) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
