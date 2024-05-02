package com.grigorievfinance.traderdiary.web;

import com.grigorievfinance.traderdiary.model.AbstractBaseEntity;

import static com.grigorievfinance.traderdiary.util.UserUtil.DEFAULT_BALANCE;

public class SecurityUtil {
    private static int id = AbstractBaseEntity.START_SEQ;

    public SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static double authUserMaxLoss() {
        return DEFAULT_BALANCE;
    }
}
