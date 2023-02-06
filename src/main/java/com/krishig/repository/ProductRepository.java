package com.krishig.repository;

import com.krishig.dto.res.ProductResDto;
import com.krishig.entity.Category;
import com.krishig.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product p where p.category_id =?1", nativeQuery = true)
    public List<Product> findByCategory(Long categoryId);

    @Query(value = "select * from product p where p.product_name like %?1%", nativeQuery = true)
    public List<Product> getProductByFilter(String keyword);
}
