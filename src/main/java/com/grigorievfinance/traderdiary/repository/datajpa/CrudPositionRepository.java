package com.grigorievfinance.traderdiary.repository.datajpa;

import com.grigorievfinance.traderdiary.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudPositionRepository extends JpaRepository<Position, Integer>{
}
