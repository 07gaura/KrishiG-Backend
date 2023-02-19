package com.krishig.repository;

import com.krishig.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from cart_product cp where cp.cp_id=?1", nativeQuery = true)
    public void deleteCartProduct(Long id);

    @Query(value = "select * from cart_product cp where cp.product_id=?1 and cp.status='open'", nativeQuery = true)
    public Optional<CartProduct> findByProduct(Long productId);

    @Query(value = "select * from cart_product cp where cp.cart_id=?1 and cp.status='open'", nativeQuery = true)
    public List<CartProduct> findByCartItem(Long cartItemId);
}
