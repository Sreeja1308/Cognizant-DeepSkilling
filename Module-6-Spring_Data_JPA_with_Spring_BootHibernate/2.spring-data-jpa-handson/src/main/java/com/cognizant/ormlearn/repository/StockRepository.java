package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // Hands-on 2: Get all stock details of Facebook in September 2019
    List<Stock> findByCodeAndDateBetween(String code, Date startDate, Date endDate);

    // FIXED: Use BigDecimal instead of double
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal price);

    // Hands-on 2: Find top 3 dates with highest volume
    List<Stock> findTop3ByOrderByVolumeDesc();

    // Hands-on 2: Identify three dates when Netflix stocks were the lowest
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}