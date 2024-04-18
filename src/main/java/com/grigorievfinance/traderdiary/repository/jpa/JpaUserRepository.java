package com.grigorievfinance.traderdiary.repository.jpa;

import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.repository.UserRepository;
import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return entityManager.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = entityManager.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createNamedQuery(User.ALL_SORTED, User.class)
                .getResultList();
    }
}
