package com.tmobile.api.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.tmobile.api.support.Base;

public class ExchangeRatesApi extends Base {

    public Response getExchangeRates() {
        return RestAssured.given(requestSpec)
                .when()
                .get("/api/exchangerates/tables/A")
                .then()
                .spec(responseSpec)
                .extract().response();
    }
}
