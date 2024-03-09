package com.grigorievfinance.traderdiary.repository.datajpa;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.repository.PositionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaPositionRepository implements PositionRepository {

    private final CrudPositionRepository crudPositionRepository;

    public DataJpaPositionRepository(CrudPositionRepository crudPositionRepository) {
        this.crudPositionRepository = crudPositionRepository;
    }

    @Override
    public Position save(Position position, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Position get(int id, int userId) {
        return null;
    }

    @Override
    public List<Position> getAll(int userId) {
        return null;
    }

    @Override
    public List<Position> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }
}
