package com.onlineShop.product.Product;

import com.onlineShop.product.Product.dto.request.ProductCreateDto;
import com.onlineShop.product.Product.dto.request.ProductUpdateDto;
import com.onlineShop.product.Product.dto.response.ProductResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductCreateDto dto);
    ProductResponseDto toProductResponseDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductUpdateDto dto, @MappingTarget Product product);
}
