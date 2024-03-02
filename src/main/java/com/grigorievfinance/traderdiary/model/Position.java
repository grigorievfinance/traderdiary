package com.grigorievfinance.traderdiary.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Position extends AbstractBaseEntity {
    private LocalDateTime dateTime;
    private String symbol;
    private double profitLoss;

    public Position() {
    }

    public Position(LocalDateTime dateTime, String symbol, double profitLoss) {
        this(null, dateTime, symbol, profitLoss);
    }

    public Position(Integer id, LocalDateTime dateTime, String symbol, double profitLoss) {
        super(id);
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", symbol='" + symbol + '\'' +
                ", profitLoss=" + profitLoss +
                '}';
    }
}
