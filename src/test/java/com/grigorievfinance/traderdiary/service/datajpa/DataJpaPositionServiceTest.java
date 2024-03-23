package com.grigorievfinance.traderdiary.service.datajpa;

import com.grigorievfinance.traderdiary.service.AbstractPositionServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.grigorievfinance.traderdiary.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaPositionServiceTest extends AbstractPositionServiceTest {
}
