package com.grigorievfinance.traderdiary;

import com.grigorievfinance.traderdiary.model.Role;
import com.grigorievfinance.traderdiary.model.User;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.grigorievfinance.traderdiary.PositionTestData.*;
import static com.grigorievfinance.traderdiary.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "positions");
    public static MatcherFactory.Matcher<User> USER_WITH_POSITIONSS_MATCHER =
            MatcherFactory.usingAssertions(User.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("registered", "positions.user").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int GUEST_ID = START_SEQ + 2;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User", "user@mail.com", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@mail.com", "admin", Role.ADMIN, Role.USER);
    public static final User guest = new User(GUEST_ID, "Guest", "guest@mail.com", "guest");

    static {
        user.setPositions(positions);
        admin.setPositions(List.of(adminPosition2, adminPosition1));
    }

    public static User getNew() {
        return new User(null, "New", "new@mail.com", "newPass", false, new Date(), Collections.singleton(Role.USER), 1000);
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setBalance(330);
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
