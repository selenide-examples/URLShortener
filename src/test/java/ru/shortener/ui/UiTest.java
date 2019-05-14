package ru.shortener.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;
import ru.shortener.ShortenerApp;
import ru.shortener.ui.pages.MainPage;
import ru.shortener.ui.pages.ResultPage;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.junit.Assert.assertEquals;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShortenerApp.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = UiTest.Initializer.class)
public class UiTest {

    @LocalServerPort
    private int port;

    private static final String URL = "http://yandex.ru";

    @Rule
    public BrowserWebDriverContainer chrome =
            new BrowserWebDriverContainer()
                    .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING,
                            new File("build"))
                    .withCapabilities(DesiredCapabilities.chrome());

    @Before
    public void setUp() {
        Configuration.baseUrl = "http://host.testcontainers.internal:" + port + "/";
        RemoteWebDriver driver =
                chrome.getWebDriver();
        WebDriverRunner.setWebDriver(driver);
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

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            applicationContext.addApplicationListener((ApplicationListener<WebServerInitializedEvent>) event -> {
                Testcontainers.exposeHostPorts(event.getWebServer().getPort());
            });
        }
    }
}
