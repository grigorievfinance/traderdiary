package com.grigorievfinance.traderdiary.model;

import java.time.LocalDateTime;

public class PositionTo {
    private final LocalDateTime dateTime;
    private final String symbol;
    private final double profitLoss;
    private final boolean profitable;

    public PositionTo(LocalDateTime dateTime, String symbol, double profitLoss, boolean profitable) {
        this.dateTime = dateTime;
        this.symbol = symbol;
        this.profitLoss = profitLoss;
        this.profitable = profitable;
    }

    @Override
    public String toString() {
        return "PositionTo{" +
                "dateTime=" + dateTime +
                ", symbol='" + symbol + '\'' +
                ", profitLoss=" + profitLoss +
                ", profitable=" + profitable +
                '}';
    }
}
