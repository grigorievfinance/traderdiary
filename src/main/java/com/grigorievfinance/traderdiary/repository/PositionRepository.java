package com.grigorievfinance.traderdiary.repository;

import com.grigorievfinance.traderdiary.model.Position;

import java.util.Collection;

public interface PositionRepository {
    Position save(Position position);

    boolean delete(int id);

    Position get(int id);

    Collection<Position> getAll();
}
