package qa.guru.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
    @Step("Open repository {repositoryName}")
    public void openRepository(String repositoryName) {
        open(repositoryName);
    }

    @Step("Open Issues tab")
    public void openIssuesTab(String repositoryName) {
        $("a[href='/" + repositoryName + "/issues']").click();
    }

    @Step("Check issue title {issueName}")
    public void checkIssueName(String issueName) {
        $(withText(issueName)).should(Condition.exist);
    }
}
