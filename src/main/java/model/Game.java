package model;

import java.util.ArrayList;

public class Game {
    private String status;
    private ArrayList<Task> tasks;
    private int currentIndex;

    public Game() {
        tasks = new ArrayList<Task>();
        tasks.add(new Task());
        tasks.add(new Task());
        currentIndex = 0;
    }

    public Task getCurrentTask() {
        if (currentIndex == -1 || currentIndex >= tasks.size())
            return null;
        return tasks.get(currentIndex);
    }

    public boolean checkAnswer(ArrayList<Integer> answer) {
        if (answer.get(0) == 1) {
            status = "Верно!";
            currentIndex++;
        }
        else {
            status = "Вы ошиблись!";
        }
        return true;
    }

    public String getMessage() {
        return status;
    }
}
