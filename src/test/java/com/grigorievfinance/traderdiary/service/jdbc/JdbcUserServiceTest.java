package com.grigorievfinance.traderdiary.service.jdbc;

import com.grigorievfinance.traderdiary.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.grigorievfinance.traderdiary.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
