package com.quizzy.quizzy.payload.request;

public class StatisticRequest {
    private int first_places;
    private int games_played;
    private int points;
    private int ranking;

    public int getFirst_places() {
        return first_places;
    }

    public void setFirst_places(int first_places) {
        this.first_places = first_places;
    }

    public int getGames_played() {
        return games_played;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
