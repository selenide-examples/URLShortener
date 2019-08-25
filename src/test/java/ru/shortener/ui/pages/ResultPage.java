package ru.shortener.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ResultPage {

    public SelenideElement getAlertMessage() {
        return $(".alert-success");
    }

    public SelenideElement getOldLink() {
        return $("#old-link");
    }

    public SelenideElement getNewLink() {
        return $("#new-link");
    }

    public SelenideElement getInfoElement() {
        return $("#result-info");
    }
}
