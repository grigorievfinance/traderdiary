package com.grigorievfinance.traderdiary.web.user;

import com.grigorievfinance.traderdiary.model.User;

import static com.grigorievfinance.traderdiary.web.SecurityUtil.authUserId;

public class ProfileRestController extends AbstractUserController {
    public User get() {
        return super.get(authUserId());
    }
    public void delete() {
        super.delete(authUserId());
    }
    public void update(User user) {
        super.update(user, authUserId());
    }
}
