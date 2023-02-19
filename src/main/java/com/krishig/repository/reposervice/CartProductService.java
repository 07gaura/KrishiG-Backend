package com.krishig.repository.reposervice;

import com.krishig.dto.res.CartProductResDto;
import com.krishig.entity.CartItem;
import com.krishig.entity.CartProduct;
import com.krishig.entity.Product;
import com.krishig.entity.User;
import com.krishig.repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartProductService extends BaseRepoService<CartProduct, Long> {

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    EntityManager entityManager;

    @Override
    protected JpaRepository<CartProduct, Long> getRepository() {
        return cartProductRepository;
    }

    @Override
    protected Class<CartProduct> getEntityClass() {
        return CartProduct.class;
    }

    public List<CartProduct> getProductsFromCart(Long userId) {
        String status = "open";
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CartProduct> criteriaBuilderQuery = criteriaBuilder.createQuery(CartProduct.class);
        Root<CartProduct> root = criteriaBuilderQuery.from(CartProduct.class);
        List<Predicate> predicates = new ArrayList<>();
        Join<CartProduct, CartItem> cartProductJoin = root.join("cartItem");
        predicates.add(criteriaBuilder.equal(cartProductJoin.get("user").get("id"), userId));
        criteriaBuilderQuery.where(criteriaBuilder.equal(root.get("status"), status));
        criteriaBuilderQuery.orderBy(criteriaBuilder.desc(root.get("cpId")));
        List<CartProduct> results = entityManager.createQuery(criteriaBuilderQuery).getResultList();
        return results;
    }
}
