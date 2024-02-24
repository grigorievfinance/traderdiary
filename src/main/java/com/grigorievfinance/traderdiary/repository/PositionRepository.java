package com.grigorievfinance.traderdiary.repository;

import com.grigorievfinance.traderdiary.model.Position;

import java.time.LocalDateTime;
import java.util.List;

public interface PositionRepository {
    Position save(Position position, int userId);

    boolean delete(int id, int userId);

    Position get(int id, int userId);

    List<Position> getAll(int userId);

    List<Position> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
