package com.grigorievfinance.traderdiary.repository.inmemory;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.repository.PositionRepository;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
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
    public Collection<Position> getAll(int userId) {
        Map<Integer, Position> positions = usersPositionsMap.get(userId);
        return CollectionUtils.isEmpty(positions) ? Collections.emptyList() :
                positions.values().stream()
                        .sorted(Comparator.comparing(Position::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}
