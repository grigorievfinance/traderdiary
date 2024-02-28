package com.grigorievfinance.traderdiary.web;

import com.grigorievfinance.traderdiary.model.AbstractBaseEntity;

import static com.grigorievfinance.traderdiary.util.PositionUtil.MAX_LOSS;

public class SecurityUtil {
    private static int id = AbstractBaseEntity.START_SEQ;
    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static double authUserMaxLoss() {
        return MAX_LOSS;
    }
}
