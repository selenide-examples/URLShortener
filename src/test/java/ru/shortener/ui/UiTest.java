package ru.shortener.ui;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.shortener.ShortenerApp;
import ru.shortener.ui.pages.MainPage;
import ru.shortener.ui.pages.ResultPage;

import static com.codeborne.selenide.Browsers.HTMLUNIT;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.junit.Assert.assertEquals;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShortenerApp.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UiTest {

    private static final String URL = "http://yandex.ru";

    @Before
    public void setUp() {
        Configuration.browser = HTMLUNIT;
        Configuration.fastSetValue = true;
    }

    @Test
    public void checkTitle() {
        open("/");
        assertEquals("URLShortener", title());
    }

    @Test
    public void checkMainPage() {
        final MainPage mainPage = open("/", MainPage.class);
        mainPage.getName().shouldHave(text("URLShortener"));
        mainPage.getDescription().shouldHave(text("Данный сервис делает длинные ссылки короткими."));
    }

    @Test
    public void addUrl() {
        final MainPage mainPage = open("/", MainPage.class);
        final ResultPage resultPage = mainPage.enterUrl(URL);
        resultPage.getAlertMessage().shouldHave(text("Ссылка уменьшена!"));
        resultPage.getOldLink().shouldHave(text(URL));
        resultPage.getNewLink().shouldHave(matchText("^http://localhost:8080/([0-9A-Za-z]){1}$"));
        resultPage.getInfoElement().find("span").shouldHave(text("Скопируйте ссылку отсюда"));
        resultPage.getInfoElement().find("input").shouldHave(value("http://localhost:8080/"));
    }
}
