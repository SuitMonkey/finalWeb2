
package com.pucmm.web2.Repository;

import com.pucmm.web2.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProductId(Integer productId);

    @Query("select p from Product p where p.productName = :name")
    List<Product> findByName(@Param("name") String productName);

    @Query("select p from Product p where p.supplier = :supplier")
    List<Product> findBySupplier(@Param("supplier") String supplier);

    @Query("select p from Product p where p.productPrice between :low and :high")
    List<Product> findByPriceRange(@Param("low") Float minPrice, @Param("high") Float maxPrice);
}
