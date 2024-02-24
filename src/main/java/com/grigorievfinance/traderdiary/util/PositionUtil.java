package com.grigorievfinance.traderdiary.util;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.model.PositionTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PositionUtil {
    public static final double INITIAL_DEPOSIT = 1000;

    public static List<PositionTo> getTos(Collection<Position> positions, double maxProfitLoss) {
        return filterByPredicate(positions, maxProfitLoss, position -> true);
    }

    public static List<PositionTo> getFilteredTos(List<Position> positions, double profitLoss, LocalTime startTime, LocalTime endTime) {
        return filterByPredicate(positions, profitLoss, position -> Util.isBetweenHalfOpen(position.getTime(), startTime, endTime));
    }

    public static List<PositionTo> filterByPredicate(Collection<Position> positions, double maxProfitLoss, Predicate<Position> filter) {
        Map<LocalDate, Double> profitSumByDate = positions.stream()
                .collect(
                        Collectors.groupingBy(Position::getDate, Collectors.summingDouble(Position::getProfitLoss))
                );
        return positions.stream()
                .filter(filter)
                .map(position -> createTo(position, profitSumByDate.get(position.getDate()) > maxProfitLoss))
                .collect(Collectors.toList());
    }

    private static PositionTo createTo(Position position, boolean profitable) {
        return new PositionTo(position.getId(), position.getDateTime(), position.getSymbol(), position.getProfitLoss(), profitable);
    }
}
