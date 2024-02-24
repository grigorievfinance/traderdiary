package com.grigorievfinance.traderdiary.repository.inmemory;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.repository.PositionRepository;
import com.grigorievfinance.traderdiary.util.Util;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryPositionRepository implements PositionRepository {
    private final Map<Integer, Map<Integer, Position>> usersPositionsMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Position save(Position position, int userId) {
        Map<Integer, Position> positions = usersPositionsMap.computeIfAbsent(userId, uId -> new ConcurrentHashMap<>());
        if (position.isNew()) {
            position.setId(counter.incrementAndGet());
            positions.put(position.getId(), position);
            return position;
        }
        return positions.computeIfPresent(position.getId(), (id, olPosition) -> position);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Position> positions = usersPositionsMap.get(userId);
        return positions != null && positions.remove(id) != null;
    }

    @Override
    public Position get(int id, int userId) {
        Map<Integer, Position> positions = usersPositionsMap.get(userId);
        return positions == null ? null : positions.get(id);
    }

    @Override
    public List<Position> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return filterByPredicate(userId, position -> Util.isBetweenHalfOpen(position.getDateTime(), startDateTime, endDateTime));
    }

    @Override
    public List<Position> getAll(int userId) {
        return filterByPredicate(userId, position -> true);
    }

    private List<Position> filterByPredicate(int userId, Predicate<Position> filter) {
        Map<Integer, Position> positions = usersPositionsMap.get(userId);
        return CollectionUtils.isEmpty(positions) ? Collections.emptyList() :
                positions.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Position::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}
