package ru.geekbrains.lesson5;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {

    boolean winner = false;

    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public boolean hasWinner() {
        return winner;
    }
    public void setWinner() {
        winner = true;
    }
}