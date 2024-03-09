package com.grigorievfinance.traderdiary.repository.inmemory;

import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.grigorievfinance.traderdiary.UserTestData.*;

@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {
    public void init() {
        map.clear();
        put(user);
        put(admin);
        put(guest);
        counter.getAndSet(GUEST_ID + 1);
    }
    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .toList();
    }

    @Override
    public User getByEmail(String email) {
        Objects.requireNonNull(email, "email must not be null");
        return getCollection().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }
}
