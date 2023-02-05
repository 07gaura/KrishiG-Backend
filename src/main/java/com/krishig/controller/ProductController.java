package com.krishig.controller;

import com.krishig.dto.res.CategoryResDto;
import com.krishig.dto.res.ProductResDto;
import com.krishig.entity.Category;
import com.krishig.entity.Product;
import com.krishig.service.CategoryService;
import com.krishig.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{categoryId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ProductResDto>> getProductByCategory(@PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        List<ProductResDto> lstProduct = productService.getProductByCategory(category);
        return ResponseEntity.ok(lstProduct);
    }

    @GetMapping("/category")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<CategoryResDto>> getAllCategory() {
        List<CategoryResDto> lstCategoryRes = categoryService.getAllCategory();
        return ResponseEntity.ok(lstCategoryRes);
    }

    @GetMapping("/{keyword}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ProductResDto>> getFilterByProduct(@PathVariable("keyword") String keyword) {
        List<ProductResDto> lstProduct = productService.getFilterByProduct(keyword);
        return ResponseEntity.ok(lstProduct);
    }
}
