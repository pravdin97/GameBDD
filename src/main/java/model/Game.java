package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private String status;
    private ArrayList<Task> tasks;
    private int currentIndex;
    private String pathToAnswerFolder, pathToTaskFolder;
    private int taskNum;

    public Game() {
        tasks = new ArrayList<Task>();
        currentIndex = -1;
    }

    public void setFolders(String pathToTaskFolder, String pathToAnswerFolder) {
        this.pathToAnswerFolder = pathToAnswerFolder;
        this.pathToTaskFolder = pathToTaskFolder;
    }

    public Task getCurrentTask() {
        if (currentIndex == -1 || currentIndex >= tasks.size())
            return null;
        return tasks.get(currentIndex);
    }

    public boolean checkAnswer(ArrayList<Integer> answer) {
        ArrayList<Integer> realAnswers = tasks.get(currentIndex).getErrorLineIndexes();
        int countGoodAnswers = 0;
        for (Integer ans: answer) {
            if (realAnswers.contains(ans))
                countGoodAnswers++;
        }
        if (countGoodAnswers != realAnswers.size()) {
            status = "Вы ошиблись!";
            return false;
        }
        status = "Верно!";
        currentIndex++;
        return true;
    }

    public String getMessage() {
        return status;
    }

    public void readTasks() {
        Scanner scanner;
        boolean end = false;
        int i = 1;
        while (!end)
        {
            FileReader tsk = null;
            try {
                tsk = new FileReader(pathToTaskFolder + "/" + i);
            } catch (FileNotFoundException e) {
                end = true;
            }
            if (!end) {
                scanner = new Scanner(tsk);
                ArrayList<String> text = new ArrayList<String>();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    text.add(line);
                }
                Task task = new Task(text);
                tasks.add(task);
                i++;
            }
        }
        taskNum = i-1;
        currentIndex = 0;
    }

    public void readAnswers() {
        Scanner scanner;
        for (int i = 1; i <= taskNum; i++)
        {
            FileReader ans = null;
            try {
                ans = new FileReader(pathToAnswerFolder + "/" + i);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (ans == null)
                return;
            scanner = new Scanner(ans);
            ArrayList<Integer> errorLines = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                Integer index = scanner.nextInt();
                errorLines.add(index);
            }
            tasks.get(i-1).setErrorLineIndexes(errorLines);
        }
        currentIndex = 0;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
