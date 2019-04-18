import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Game;
import org.junit.Assert;

public class ReadTasksTest {
    private String pathToTaskFolder;
    private String pathToAnswerFolder;
    private Game game;

    @Given("^Путь к папке с заданиями на диске$")
    public void путьКПапкеСЗаданиямиНаДиске() {
        pathToTaskFolder = getClass().getResource("/task").getPath();
    }

    @Given("^Путь к папке с ответами на задания на диске$")
    public void путьКПапкеСОтветамиНаЗаданияНаДиске() {
        pathToAnswerFolder = getClass().getResource("/answer").getPath();
    }

    @When("^Создается экземпляр игры$")
    public void создаетсяЭкземплярИгры() {
        game = new Game();
    }

    @Then("^Происходит чтение всех заданий$")
    public void происходитЧтениеВсехЗаданий() {
        game.readTasks(pathToTaskFolder);
        Assert.assertNotEquals(0, game.getAllTasks().size());
    }

    @And("^Происходит чтение всех ответов к заданиям$")
    public void происходитЧтениеВсехОтветовКЗаданиям() {
        game.readAnswers(pathToAnswerFolder);
        Assert.assertNotEquals(0, game.getAllTasks().get(0).getErrorLineIndexes().size());
    }
}
