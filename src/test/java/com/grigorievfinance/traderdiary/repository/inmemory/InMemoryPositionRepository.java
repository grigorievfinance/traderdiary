package com.grigorievfinance.traderdiary.repository.inmemory;

import com.grigorievfinance.traderdiary.PositionTestData;
import com.grigorievfinance.traderdiary.UserTestData;
import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.repository.PositionRepository;
import com.grigorievfinance.traderdiary.util.Util;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryPositionRepository implements PositionRepository {
    private final Map<Integer, InMemoryBaseRepository<Position>> usersPositionsMap = new ConcurrentHashMap<>();

    {
        InMemoryBaseRepository<Position> userPositions = new InMemoryBaseRepository<>();
        PositionTestData.positions.forEach(userPositions::put);
        usersPositionsMap.put(UserTestData.USER_ID, userPositions);
    }

    @Override
    public Position save(Position position, int userId) {
        Objects.requireNonNull(position, "position must not be null");
        InMemoryBaseRepository<Position> positions = usersPositionsMap.computeIfAbsent(userId, uId -> new InMemoryBaseRepository<>());
        return positions.save(position);
    }

    @Override
    public boolean delete(int id, int userId) {
        InMemoryBaseRepository<Position> positions = usersPositionsMap.get(userId);
        return positions != null && positions.delete(id);
    }

    @Override
    public Position get(int id, int userId) {
        InMemoryBaseRepository<Position> positions = usersPositionsMap.get(userId);
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
        InMemoryBaseRepository<Position> positions = usersPositionsMap.get(userId);
        return positions == null ? Collections.emptyList() :
                positions.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Position::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}
