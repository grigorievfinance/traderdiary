package com.grigorievfinance.traderdiary.repository.datajpa;

import com.grigorievfinance.traderdiary.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudPositionRepository extends JpaRepository<Position, Integer>{

    @Modifying
    @Transactional
    @Query("DELETE FROM Position p WHERE p.id=:id AND p.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT  p FROM Position p WHERE p.user.id=:userId ORDER BY p.dateTime DESC")
    List<Position> getAll(@Param("userId") int userId);

    @Query("SELECT p FROM Position p WHERE p.user.id=:userId AND p.dateTime>=:startDate AND p.dateTime<:endDate ORDER BY p.dateTime DESC")
    List<Position> getBetweenHalfOpen(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT p FROM Position p JOIN FETCH p.user WHERE p.id = ?1 AND p.user.id = ?2")
    Position getWithUser(int id, int userId);
}
