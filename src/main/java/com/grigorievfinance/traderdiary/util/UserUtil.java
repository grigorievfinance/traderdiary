package com.grigorievfinance.traderdiary.util;

import com.grigorievfinance.traderdiary.model.Role;
import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.to.UserTo;

public class UserUtil {

    public static final int DEFAULT_BALANCE = 2000;

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
