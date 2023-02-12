package com.krishig.repository;

import com.krishig.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from cart_product cp where cp.cp_id=?1", nativeQuery = true)
    public void deleteCartProduct(Long id);
}
