package com.grigorievfinance.traderdiary.util;

import com.grigorievfinance.traderdiary.model.Role;
import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.to.UserTo;

public class UserUtil {

    public static final double DEFAULT_BALANCE = 2000;

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), userTo.getBalance(), Role.USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getBalance());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setBalance(userTo.getBalance());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
