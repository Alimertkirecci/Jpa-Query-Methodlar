package com.example.repositories;

import com.example.entities.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPidNotIn(Collection<Long> pids);

    List<Product> findByTitleLikeIgnoreCase(String title);

    List<Product> findByDescriptionStartsWithIgnoreCase(String description);

    List<Product> findByTitleNotLikeIgnoreCase(String title);

    List<Product> findByDescriptionEndsWithIgnoreCase(String description);

    List<Product> findByTitleIgnoreCaseAndPriceBetween(String title, BigDecimal priceStart, BigDecimal priceEnd);

    List<Product> findByPriceLessThan(BigDecimal price);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal priceStart, BigDecimal priceEnd);


}