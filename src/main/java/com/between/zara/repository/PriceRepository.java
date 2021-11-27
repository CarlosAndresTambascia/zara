package com.between.zara.repository;

import com.between.zara.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query(value = "SELECT p FROM Price p " +
            "WHERE p.productId = :productId AND p.brand.id = :brandId " +
            "AND :applicationDate BETWEEN p.startDate AND p.endDate")
    List<Price> searchPriceForSpecificDate(@Param("productId") Long productId,
                                           @Param("brandId") Long brandId,
                                           @Param("applicationDate") Date applicationDate);
}
