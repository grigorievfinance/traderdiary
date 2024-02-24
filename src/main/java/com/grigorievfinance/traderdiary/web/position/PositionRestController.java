package com.grigorievfinance.traderdiary.web.position;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.service.PositionService;
import com.grigorievfinance.traderdiary.to.PositionTo;
import com.grigorievfinance.traderdiary.util.PositionUtil;
import com.grigorievfinance.traderdiary.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.grigorievfinance.traderdiary.util.ValidationUtil.assureIdConsistent;
import static com.grigorievfinance.traderdiary.util.ValidationUtil.checkNew;

@Controller
public class PositionRestController {
    private static final Logger log = LoggerFactory.getLogger(PositionRestController.class);

    private final PositionService positionService;

    public PositionRestController(PositionService positionService) {
        this.positionService = positionService;
    }

    public Position get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get position {} for user {}", id, userId);
        return positionService.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete position {} for user {}", id, userId);
        positionService.delete(id, userId);
    }

    public List<PositionTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return PositionUtil.getTos(positionService.getAll(userId), SecurityUtil.authUserMaxLoss());
    }

    public Position create(Position position) {
        int userId = SecurityUtil.authUserId();
        checkNew(position);
        log.info("create {} for user {}", position, userId);
        return positionService.create(position, userId);
    }

    public void update(Position position, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(position, id);
        log.info("update {} for user {}", position, userId);
        positionService.update(position, id);
    }

    public List<PositionTo> getBetween(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                       @Nullable LocalDate endDate, @Nullable LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);
        List<Position> positionsDateFiltered = positionService.getBetweenInclusive(startDate, endDate, userId);
        return PositionUtil.getFilteredTos(positionsDateFiltered, SecurityUtil.authUserMaxLoss(), startTime, endTime);
    }
}
