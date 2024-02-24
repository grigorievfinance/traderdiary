package com.grigorievfinance.traderdiary.repository;

import com.grigorievfinance.traderdiary.model.Position;

import java.util.Collection;

public interface PositionRepository {
    Position save(Position position, int userId);

    boolean delete(int id, int userId);

    Position get(int id, int userId);

    Collection<Position> getAll(int userId);
}
