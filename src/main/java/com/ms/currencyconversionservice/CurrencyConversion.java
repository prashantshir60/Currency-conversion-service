package com.ms.currencyconversionservice;

import java.math.BigDecimal;

public class CurrencyConversion {
    private int id;
    private String from;
    private String to;
    private BigDecimal conversionRate;
    private int quantity;
    private BigDecimal calculatedValue;
    private String environment;


    public  CurrencyConversion(){}

    public CurrencyConversion(int id, String from, String to, BigDecimal conversionRate, int quantity) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionRate = conversionRate;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCalculatedValue() {
        calculatedValue = this.conversionRate.multiply(BigDecimal.valueOf(getQuantity())) ;
        return calculatedValue;
    }

    public void setCalculatedValue(BigDecimal calculatedValue) {
        this.calculatedValue = calculatedValue;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }
}
