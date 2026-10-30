package com.onlineShop.product;

import com.onlineShop.exception.product.HandleProductNotFound;
import com.onlineShop.product.dto.request.ProductCreateDto;
import com.onlineShop.product.dto.request.ProductFilter;
import com.onlineShop.product.dto.request.ProductUpdateDto;
import com.onlineShop.product.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private Product productIsExists(UUID productId){
        return productRepository.findById(productId).orElseThrow(
                () -> new HandleProductNotFound(productId)
        );
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProducts(ProductFilter filter, Pageable pageable) {
        Specification<Product> spec = Specification.allOf(
                ProductSpecifications.hasCategory(filter.categoryId()),
                ProductSpecifications.priceGte(filter.minPrice()),
                ProductSpecifications.priceLte(filter.maxPrice())
        );
        Page<Product> products = productRepository.findAllWithCategory(spec, pageable);
        return products.map(productMapper::toProductResponseDto);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getProduct(UUID productId) {
        Product product = productIsExists(productId);
        return productMapper.toProductResponseDto(product);
    }

    public ProductResponseDto createProduct(ProductCreateDto productCreateDto) {
        Product product = productMapper.toProduct(productCreateDto);
        productRepository.save(product);
        return productMapper.toProductResponseDto(product);
    }

    public ProductResponseDto updateProduct(UUID productId, ProductUpdateDto productUpdateDto) {
        Product product = productIsExists(productId);
        productMapper.updateProductFromDto(productUpdateDto, product);
        return productMapper.toProductResponseDto(product);
    }

    public void deleteProduct(UUID productId) {
        productIsExists(productId);
        productRepository.deleteById(productId);
    }

}
