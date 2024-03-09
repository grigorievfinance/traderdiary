package com.grigorievfinance.traderdiary.repository.jpa;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.repository.PositionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaPositionRepository implements PositionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Position save(Position position, int userId) {
        position.setUser(entityManager.getReference(User.class, userId));
        if (position.isNew()) {
            entityManager.persist(position);
            return position;
        }
        return get(position.id(), userId) == null ? null : entityManager.merge(position);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return entityManager.createNamedQuery(Position.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Position get(int id, int userId) {
        Position position = entityManager.find(Position.class, id);
        return position != null && position.getUser().getId() == userId ? position : null;
    }

    @Override
    public List<Position> getAll(int userId) {
        return entityManager.createNamedQuery(Position.ALL_SORTED, Position.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Position> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return entityManager.createNamedQuery(Position.GET_BETWEEN, Position.class)
                .setParameter("userId", userId)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }
}
