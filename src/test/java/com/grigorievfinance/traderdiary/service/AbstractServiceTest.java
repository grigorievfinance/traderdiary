package com.grigorievfinance.traderdiary.service;

import com.grigorievfinance.traderdiary.ActiveDbProfileResolver;
import com.grigorievfinance.traderdiary.TimingExtension;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static com.grigorievfinance.traderdiary.util.ValidationUtil.getRootCause;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ExtendWith(TimingExtension.class)
public abstract class AbstractServiceTest {

    protected <T extends Throwable> void validateRootCause(Class<T> rootExceptionClass, Runnable runnable) {
        assertThrows(rootExceptionClass, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw getRootCause(e);
            }
        });
    }
}
