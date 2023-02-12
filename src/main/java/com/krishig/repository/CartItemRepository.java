package com.krishig.repository;

import com.krishig.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "select count(*) from cart_items\n" +
            "where action='open' and user_id=?1\n" +
            "group by user_id", nativeQuery = true)
    public Long findCartItemsCount(Long userId);

    public CartItem findByUserId(Long userId);
}
