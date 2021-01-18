package com.quizzy.quizzy.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public Room(String gameId, List<String> players) {
        this.gameId = gameId;
        this.players = players;
    }

    private String gameId;

    private List<String> players = new ArrayList<>();

    private String owner;

    public Room() {
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
