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

    public List<CartProductResDto> getProductsFromCart(Long userId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CartProductResDto> criteriaBuilderQuery = criteriaBuilder.createQuery(CartProductResDto.class);
        Root<CartItem> root = criteriaBuilderQuery.from(CartItem.class);
        List<Predicate> predicates = new ArrayList<>();
        Join<CartItem, User> userCartJoin = root.join("user");
        Join<CartItem, CartProduct> cartProductJoin = root.join("cartProduct");
        Join<CartProduct, Product> productJoin = cartProductJoin.join("product");
        predicates.add(criteriaBuilder.equal(cartProductJoin.get("status"), "open"));
        predicates.add(criteriaBuilder.equal(userCartJoin.get("id"), userId));
        CompoundSelection<CartProductResDto> selection = null;
        selection = criteriaBuilder.construct(CartProductResDto.class,
               userCartJoin.get("id"),
                root.get("cartId"),
                cartProductJoin.get("cpId"),
                productJoin.get("id"),
                productJoin.get("productName"),
                productJoin.get("actualPrice"),
                productJoin.get("discount"),
                productJoin.get("discountPrice"),
                cartProductJoin.get("status"),
                cartProductJoin.get("quantity")
                );
        criteriaBuilderQuery.select(selection).where(predicates.toArray(new Predicate[]{}));
        criteriaBuilderQuery.orderBy(criteriaBuilder.desc(cartProductJoin.get("cpId")));
        List<CartProductResDto> results = entityManager.createQuery(criteriaBuilderQuery).getResultList();
        System.out.println(results);
        return results;
    }
}
