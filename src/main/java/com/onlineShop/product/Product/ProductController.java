package com.onlineShop.product.Product;

import com.onlineShop.product.Product.dto.request.ProductCreateDto;
import com.onlineShop.product.Product.dto.request.ProductFilter;
import com.onlineShop.product.Product.dto.request.ProductUpdateDto;
import com.onlineShop.product.Product.dto.response.ProductResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Product management")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponseDto createProduct(@Valid @RequestBody ProductCreateDto dto) {
        return productService.createProduct(dto);
    }

    @PatchMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponseDto updateProduct(@Valid @RequestBody ProductUpdateDto dto, @PathVariable UUID productId) {
        return productService.updateProduct(productId, dto);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getProduct(@PathVariable UUID productId) {
        return productService.getProduct(productId);
    }

    @GetMapping
    public Page<ProductResponseDto> getProducts(ProductFilter filter, Pageable pageable) {
        return productService.getProducts(filter, pageable);
    }

}
