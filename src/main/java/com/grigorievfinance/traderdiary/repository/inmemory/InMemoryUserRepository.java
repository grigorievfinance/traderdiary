package com.grigorievfinance.traderdiary.repository.inmemory;

import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.repository.UserRepository;

import java.util.List;

public class InMemoryUserRepository implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
