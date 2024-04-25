package com.grigorievfinance.traderdiary.to;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.Objects;

public class PositionTo {
    private final Integer id;
    private final LocalDateTime dateTime;
    private final String symbol;
    private final double profitLoss;
    private final boolean profitable;

    @ConstructorProperties({"id", "dateTime", "description", "profitLoss", "profitable"})
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionTo that = (PositionTo) o;
        return Double.compare(profitLoss, that.profitLoss) == 0 && profitable == that.profitable && Objects.equals(id, that.id) && Objects.equals(dateTime, that.dateTime) && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, symbol, profitLoss, profitable);
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
