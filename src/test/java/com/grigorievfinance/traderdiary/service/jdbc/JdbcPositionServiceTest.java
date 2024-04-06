package com.grigorievfinance.traderdiary.service.jdbc;

import com.grigorievfinance.traderdiary.service.AbstractPositionServiceTest;
import org.junit.Ignore;
import org.springframework.test.context.ActiveProfiles;

import static com.grigorievfinance.traderdiary.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcPositionServiceTest extends AbstractPositionServiceTest {
    @Override
    @Ignore
    public void createWithException() throws Exception {
    }
}
