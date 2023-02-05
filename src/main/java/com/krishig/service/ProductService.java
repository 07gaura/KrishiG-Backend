package com.krishig.service;

import com.krishig.dto.res.ProductResDto;
import com.krishig.entity.Category;
import com.krishig.entity.Product;

import java.util.List;

public interface ProductService {

    public List<ProductResDto> getProductByCategory(Category category);

    public List<ProductResDto> getFilterByProduct(String keyword);
}
