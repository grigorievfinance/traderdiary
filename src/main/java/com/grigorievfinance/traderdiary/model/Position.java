package com.grigorievfinance.traderdiary.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Position {
    private final LocalDateTime dateTime;
    private final String symbol;
    private final double profitLoss;

    public Position(LocalDateTime dateTime, String symbol, double profitLoss) {
        this.dateTime = dateTime;
        this.symbol = symbol;
        this.profitLoss = profitLoss;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
