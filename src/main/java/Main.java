import model.Game;
import model.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.setFolders(Main.class.getResource("/task").getPath(),
                Main.class.getResource("/answer").getPath());
        game.readTasks();
        game.readAnswers();

        while(game.getCurrentTask() != null) {
            Task currentTask = game.getCurrentTask();

            boolean res = false;
            while(!res) {
                res = oneTask(game, currentTask);
                if (!res)
                    System.out.println(game.getMessage());
            }
            System.out.println(game.getMessage());
        }

        System.out.println("Вы прошли все задания!");
    }

    private static boolean oneTask(Game game, Task currentTask) {
        System.out.println("Задание:");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> text = currentTask.getText();
        for (int i = 0; i < text.size(); i++)
            System.out.println((i + 1) + ": " + text.get(i) );

        ArrayList<Integer> answers = new ArrayList<Integer>();
        System.out.println("Введите номера строк, в которых присутствуют ошибки: \n(Любое нечисло, если больше ошибок нет)");
        while (scanner.hasNextInt()) {
            Integer answer = scanner.nextInt();
            answers.add(answer);
        }
        return game.checkAnswer(answers);
    }
}
