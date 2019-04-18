package model;

import java.util.ArrayList;

public class Task {
    private ArrayList<String> text;
    private ArrayList<Integer> errorLineIndexes;

    public Task() {
        text = new ArrayList<String>();
        errorLineIndexes = new ArrayList<Integer>();
    }

    public Task(ArrayList<String> text) {
        this.text = text;
        errorLineIndexes = new ArrayList<Integer>();
    }
    public ArrayList<String> getText() {
        return text;
    }

    public void setErrorLineIndexes(ArrayList<Integer> errorLineIndexes) {
        this.errorLineIndexes = errorLineIndexes;
    }

    public ArrayList<Integer> getErrorLineIndexes() {
        return errorLineIndexes;
    }
}
