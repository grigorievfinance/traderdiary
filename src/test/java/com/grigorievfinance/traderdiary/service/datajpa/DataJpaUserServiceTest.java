package com.grigorievfinance.traderdiary.service.datajpa;

import com.grigorievfinance.traderdiary.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.grigorievfinance.traderdiary.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
