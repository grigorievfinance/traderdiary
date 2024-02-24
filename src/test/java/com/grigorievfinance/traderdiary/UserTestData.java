package com.grigorievfinance.traderdiary;

import com.grigorievfinance.traderdiary.model.Role;
import com.grigorievfinance.traderdiary.model.User;

public class UserTestData {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final int GUEST_ID = 3;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User", "user@mail.com", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@mail.com", "admin", Role.ADMIN);
    public static final User guest = new User(GUEST_ID, "Guest", "guest@mail.com", "guest");
}
