package com.grigorievfinance.traderdiary.model;

import java.time.LocalDateTime;

public class PositionTo {
    private final Integer id;
    private final LocalDateTime dateTime;
    private final String symbol;
    private final double profitLoss;
    private final boolean profitable;

    public PositionTo(Integer id, LocalDateTime dateTime, String symbol, double profitLoss, boolean profitable) {
        this.id = id;
        this.dateTime = dateTime;
        this.symbol = symbol;
        this.profitLoss = profitLoss;
        this.profitable = profitable;
    }

    public Integer getId() {
        return id;
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

    public boolean isProfitable() {
        return profitable;
    }

    @Override
    public String toString() {
        return "PositionTo{" +
                "id=" + id +
                "dateTime=" + dateTime +
                ", symbol='" + symbol + '\'' +
                ", profitLoss=" + profitLoss +
                ", profitable=" + profitable +
                '}';
    }
}
