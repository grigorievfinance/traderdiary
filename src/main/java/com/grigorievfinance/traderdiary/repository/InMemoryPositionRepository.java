package com.grigorievfinance.traderdiary.repository;

import com.grigorievfinance.traderdiary.model.Position;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPositionRepository implements PositionRepository {
    private final Map<Integer, Position> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Position save(Position position) {
        if (position.isNew()) {
            position.setId(counter.incrementAndGet());
            repository.put(position.getId(), position);
            return position;
        }
        return repository.computeIfPresent(position.getId(), (id, olPosition) -> position);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Position get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Position> getAll() {
        return repository.values();
    }
}
