package com.grigorievfinance.traderdiary.web;

import static com.grigorievfinance.traderdiary.util.PositionUtil.MAX_LOSS;

public class SecurityUtil {
    public static int authUserId() {
        return 1;
    }

    public static double authUserMaxLoss() {
        return MAX_LOSS;
    }
}
