package com.grigorievfinance.traderdiary.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Position extends AbstractBaseEntity {
    private final LocalDateTime dateTime;
    private final String symbol;
    private final double profitLoss;

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
