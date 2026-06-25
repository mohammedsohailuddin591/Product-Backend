package com.example.products.product_api.Controller;


import com.example.products.product_api.Model.Product;
import com.example.products.product_api.Repository.ProductRepository;
import com.example.products.product_api.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.findAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name is required");
        }

        return productService.saveProduct(product);
    }
}
