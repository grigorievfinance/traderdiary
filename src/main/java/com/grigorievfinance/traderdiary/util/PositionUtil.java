package com.grigorievfinance.traderdiary.util;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.model.PositionTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PositionUtil {

    public static List<PositionTo> filtered(List<Position> positions, LocalTime startTime, LocalTime endTime, double profitLoss) {
        Map<LocalDate, Double> profitSumByDate = positions.stream()
                .collect(
                        Collectors.groupingBy(Position::getDate, Collectors.summingDouble(Position::getProfitLoss))
                );
        return positions.stream()
                .filter(position -> TimeUtil.isBetweenHalfOpen(position.getTime(), startTime, endTime))
                .map(position -> createTo(position, profitSumByDate.get(position.getDate()) > profitLoss))
                .collect(Collectors.toList());
    }

    private static PositionTo createTo(Position position, boolean profitable) {
        return new PositionTo(position.getDateTime(), position.getSymbol(), position.getProfitLoss(), profitable);
    }
}
