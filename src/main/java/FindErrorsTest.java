import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Game;
import model.Task;
import org.junit.Assert;

import java.util.ArrayList;

public class FindErrorsTest {
    private Game game;
    private Task currentTask;
    private ArrayList<Integer> answer;

    @Given("^Имеется экземпляр игры$")
    public void имеетсяЭкземплярИгры() {
        game = new Game();
        game.setFolders(getClass().getResource("/task").getPath(),
                getClass().getResource("/answer").getPath());
        game.readTasks();
        game.readAnswers();
    }

    @Given("^В игре есть текущее задание$")
    public void вИгреЕстьТекущееЗадание() {
        currentTask = game.getCurrentTask();
    }

    @When("^Пользователь вводит кооректный ответ на задание (\\d+)$")
    public void пользовательВводитКооректныйОтветНаЗадание(int arg0) {
        answer = new ArrayList<Integer>();
        answer.add(arg0);
    }

    @Then("^Проверяется корректность ответа$")
    public void проверяетсяКорректностьОтвета() {
        boolean checkResult = game.checkAnswer(answer);
        Assert.assertTrue(checkResult);
    }

    @And("^Игра сообщает пользователю, что ответ корректен$")
    public void играСообщаетПользователюЧтоОтветКорректен() {
        String expected = "Верно!";
        Assert.assertEquals(expected, game.getMessage());
        System.out.println();
    }

    @Then("^Текущим заданием становится следующее задание в игре$")
    public void текущимЗаданиемСтановитсяСледующееЗаданиеВИгре() {
        Assert.assertNotEquals(currentTask, game.getCurrentTask());
    }
    
    @When("^Пользователь вводит некооректный ответ на задание (\\d+)$")
    public void пользовательВводитНекооректныйОтветНаЗадание(int arg0) {
        answer = new ArrayList<Integer>();
        answer.add(arg0);
    }

    @And("^Игра сообщает пользователю, что ответ некорректен$")
    public void играСообщаетПользователюЧтоОтветНекорректен() {
        String expected = "Вы ошиблись!";
        Assert.assertEquals(expected, game.getMessage());
    }

    @And("^Текущиее задание остается прежним$")
    public void текущиееЗаданиеОстаетсяПрежним() {
        Assert.assertEquals(currentTask, game.getCurrentTask());
    }

    @Then("^Проверяется неверность ответа$")
    public void проверяетсяНеверностьОтвета() {
        boolean checkResult = game.checkAnswer(answer);
        Assert.assertFalse(checkResult);
    }
}
