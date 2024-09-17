package com.tmobile.api.steps;

import com.tmobile.api.API.ExchangeRatesApi;
import com.tmobile.api.support.Rate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.List;
import java.util.stream.Collectors;

public class Steps {

    private Response response;
    private ExchangeRatesApi exchangeRatesApi = new ExchangeRatesApi();

    @Given("I get current exchange rates")
    public void getExchangeRates() {
        response = exchangeRatesApi.getExchangeRates();
        Hook.logger.info("Status Code: " + response.getStatusCode());
    }

    @Then("I display rate for currency code: (.*?)$")
    public void displayRateForSpecificCode(String currencyCode) {
        Float midValue = response.jsonPath()
                .get("[0].rates.find { it.code == '" + currencyCode + "' }.mid");

        if (midValue != null) {
            Hook.logger.info("Exchange rate for {}: {}", currencyCode, midValue);
        } else {
            throw new AssertionError("Currency code " + currencyCode + " not found in the response");
        }
    }

    @Then("I display rate for currency name: (.*?)$")
    public void displayRateForSpecificCurrencyName(String currencyName) {
        Float midValue = response.jsonPath()
                .get("[0].rates.find { it.currency == '" + currencyName + "' }.mid");

        if (midValue != null) {
            Hook.logger.info("Exchange rate for {}: {}", currencyName, midValue);
        } else {
            throw new AssertionError("Currency name " + currencyName + " not found in the response");
        }
    }

    @Then("I display currencies with rate above: (.*?)$")
    public void displayRatesAboveSpecificValue(float value) {
        List<Rate> allRatesList = response.jsonPath().getList("[0].rates", Rate.class);

        List<Rate> ratesAboveValueList = allRatesList.stream()
                .filter(rate -> rate.getMid() > value)
                .collect(Collectors.toList());

        ratesAboveValueList.stream()
                .forEach(rate -> Hook.logger.info(rate.toString()));
    }

    @Then("I display currencies with rate under: (.*?)$")
    public void displayRatesUnderSpecificValue(float value) {
        List<Rate> allRatesList = response.jsonPath().getList("[0].rates", Rate.class);

        List<Rate> ratesUnderValueList = allRatesList.stream()
                .filter(rate -> rate.getMid() < value)
                .collect(Collectors.toList());

        ratesUnderValueList.stream()
                .forEach(rate -> Hook.logger.info(rate.toString()));
    }
}