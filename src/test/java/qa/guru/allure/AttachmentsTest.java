package qa.guru.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest {
    private static final String REPOSITTORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    public void testLambdaAttachments() {

        SelenideLogger.addListener("allure", new AllureSelenide()); // для отображение шагов теста в Allure
        step("Открываем главную страницу", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
        });
    }

    @Test
    public void testAnnotatedAttachments() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        WebTests steps = new WebTests();

        steps.openMainPage();
        steps.takeScreenshot();
    }
}
