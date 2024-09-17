package com.tmobile.api.support;

public class Rate {
    private String currency;
    private String code;
    private float mid;

    public Rate() {
    }

    public Rate(String currency, String code, float mid) {
        this.currency = currency;
        this.code = code;
        this.mid = mid;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public float getMid() {
        return mid;
    }

    @Override
    public String toString() {
        return "Currency: " + currency + ", Code: " + code + ", Mid: " + mid;
    }
}
