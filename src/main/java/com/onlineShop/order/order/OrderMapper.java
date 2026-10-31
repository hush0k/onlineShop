package com.onlineShop.order.order;

import com.onlineShop.order.order.dto.request.OrderUpdateDto;
import com.onlineShop.order.order.dto.response.OrderResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toOrderResponseDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderFromDto(OrderUpdateDto dto, @MappingTarget Order order);
}
