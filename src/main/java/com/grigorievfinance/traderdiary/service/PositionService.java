package com.grigorievfinance.traderdiary.service;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.repository.PositionRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.grigorievfinance.traderdiary.util.DateTimeUtil.atStartOfDayOrMin;
import static com.grigorievfinance.traderdiary.util.DateTimeUtil.atStartOfNextDayOrMax;
import static com.grigorievfinance.traderdiary.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position get(int id, int userId) {
        return checkNotFoundWithId(positionRepository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(positionRepository.delete(id, userId), id);
    }

    public List<Position> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return positionRepository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }

    public List<Position> getAll(int userId) {
        return positionRepository.getAll(userId);
    }

    public void update(Position position, int userId) {
        Assert.notNull(position, "position must not be null");
        checkNotFoundWithId(positionRepository.save(position, userId), position.getId());
    }

    public Position create(Position position, int userId) {
        Assert.notNull(position, "position must not be null");
        return positionRepository.save(position, userId);
    }
}
