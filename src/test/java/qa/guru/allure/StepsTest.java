package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    private static final String REPOSITTORY = "eroshenkoam/allure-example";
    private static final int  ISSUE =80;
    @Test
    public void testLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide()); // для отображение шагов теста в Allure
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий: " + REPOSITTORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITTORY);
            $(".header-search-input").submit();
        });
        step("Кликаем по ссылке репозитория: " + REPOSITTORY, () -> {
            $(linkText(REPOSITTORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером: "+ ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public  void testAnnotatedStep() {
        WebTests steps = new WebTests();
        steps.openMainPage();
        steps.searchForRepository(REPOSITTORY);
        steps.clickOnRepositoryLink(REPOSITTORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
