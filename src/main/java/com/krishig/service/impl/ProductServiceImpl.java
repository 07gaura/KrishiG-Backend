package com.krishig.service.impl;

import com.krishig.dto.res.ProductResDto;
import com.krishig.entity.Category;
import com.krishig.entity.Product;
import com.krishig.repository.ProductRepository;
import com.krishig.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResDto> getProductByCategory(Category category) {
        List<Product> product = productRepository.findByCategory(category.getId());
        List<ProductResDto> lstProductRes = convertEntityToDto(product);
        return lstProductRes;
    }

    @Override
    public List<ProductResDto> getFilterByProduct(String keyword) {
        List<Product> product =  productRepository.getProductByFilter(keyword);
        List<ProductResDto> lstProductRes = convertEntityToDto(product);
        return lstProductRes;
    }

    private List<ProductResDto> convertEntityToDto(List<Product> lstProduct) {
        List<ProductResDto> productResDtoList = new ArrayList<>();
        for(Product product : lstProduct) {
            ProductResDto productDto = new ProductResDto();
            productDto.setId(product.getId());
            productDto.setProductName(product.getProductName());
            productDto.setActualPrice(product.getActualPrice());
            productDto.setDescription(product.getDescription());
            productDto.setDiscount(product.getDiscount());
            productDto.setDiscountPrice(product.getDiscountPrice());
            productDto.setImageLink(product.getImageLink());
            productResDtoList.add(productDto);
        }
        return productResDtoList;
    }
}
