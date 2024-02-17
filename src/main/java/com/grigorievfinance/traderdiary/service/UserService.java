package com.grigorievfinance.traderdiary.service;

import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.repository.UserRepository;

import java.util.List;

import static com.grigorievfinance.traderdiary.util.ValidationUtil.*;

public class UserService {
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User getByEmail(String email) {
        return checkNotFound(userRepository.getByEmail(email), email);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void update(User user) {
        checkNotFoundWithId(userRepository.save(user), user.getId());
    }
}
