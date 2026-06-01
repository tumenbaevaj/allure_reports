package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GitHubIssuesTests {

    private static final String repositoryName = "tumenbaevaj/allure_reports";
    private static final String issueName = "Test issue for Allure homework";

    @BeforeAll
    static void configureTests() {
        Configuration.baseUrl = "https://github.com/";
    }

    @Test
    @Owner("tumenbaevaj")
    @Tag("WEB")
    @DisplayName("Проверка названия Issue через Selenide Listener")
    public void selenideListenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(repositoryName);
        $("a[href='/" + repositoryName + "/issues']").click();
        $(withText(issueName)).should(Condition.exist);
    }

    @Test
    @Owner("tumenbaevaj")
    @Tag("WEB")
    @DisplayName("Проверка названия Issue через Lambda steps")
    public void lambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем репозиторий " + repositoryName, () -> {
            open(repositoryName);
        });

        step("Переходим в раздел Issues", () -> {
            $("a[href='/" + repositoryName + "/issues']").click();
        });

        step("Проверяем название Issue", () -> {
            $(withText(issueName)).should(Condition.exist);
        });
    }

    @Test
    @Owner("tumenbaevaj")
    @Tag("WEB")
    @DisplayName("Проверка названия Issue с аннотацией @Step")
    public void annotatedStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();

        steps.openRepository(repositoryName);
        steps.openIssuesTab(repositoryName);
        steps.checkIssueName(issueName);
    }
}
