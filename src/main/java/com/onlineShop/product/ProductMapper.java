package com.onlineShop.product;

import com.onlineShop.product.dto.request.ProductCreateDto;
import com.onlineShop.product.dto.request.ProductUpdateDto;
import com.onlineShop.product.dto.response.ProductResponseDto;
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
