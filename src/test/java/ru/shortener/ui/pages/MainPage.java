package ru.shortener.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    public ResultPage enterUrl(String url) {
        $("#url-input").setValue(url).pressEnter();
        return page(ResultPage.class);
    }

    public SelenideElement getName() {
        return $("#name-app");
    }

    public SelenideElement getDescription() {
        return $("#description-app");
    }
}
