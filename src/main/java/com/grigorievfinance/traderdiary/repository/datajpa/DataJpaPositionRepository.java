package com.grigorievfinance.traderdiary.repository.datajpa;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.repository.PositionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaPositionRepository implements PositionRepository {

    private final CrudPositionRepository crudPositionRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaPositionRepository(CrudPositionRepository crudPositionRepository, CrudUserRepository crudUserRepository) {
        this.crudPositionRepository = crudPositionRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Position save(Position position, int userId) {
        if (!position.isNew() && get(position.id(), userId) == null) {
            return null;
        }
        position.setUser(crudUserRepository.getReferenceById(userId));
        return crudPositionRepository.save(position);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudPositionRepository.delete(id, userId) != 0;
    }

    @Override
    public Position get(int id, int userId) {
        return crudPositionRepository.findById(id)
                .filter(position -> position.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Position> getAll(int userId) {
        return crudPositionRepository.getAll(userId);
    }

    @Override
    public List<Position> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudPositionRepository.getBetweenHalfOpen(startDateTime, endDateTime, userId);
    }
}
