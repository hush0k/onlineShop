package com.onlineShop.product;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductSpecifications {

    public static Specification<Product> hasCategory(UUID categoryId) {
        return (root, query, cb) -> categoryId == null ? null :
                cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> priceGte(BigDecimal min) {
        return (root, query, cb) -> min == null ? null :
                cb.greaterThanOrEqualTo(root.get("price"), min);
    }

    public static Specification<Product> priceLte(BigDecimal max) {
        return (root, query, cb) -> max == null ? null :
                cb.lessThanOrEqualTo(root.get("price"), max);
    }

}
