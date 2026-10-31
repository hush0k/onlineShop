package com.onlineShop.order.order;

import com.onlineShop.order.order.dto.request.OrderStatusUpdateDto;
import com.onlineShop.order.order.dto.request.OrderUpdateDto;
import com.onlineShop.order.order.dto.response.OrderResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Order management")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/me")
    public Page<OrderResponseDto> getMyOrders(@AuthenticationPrincipal String email, Pageable pageable) {
        return orderService.getMyOrders(email, pageable);
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN') or @orderService.isOwner(#orderId, authentication.name)")
    public OrderResponseDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }

    @PatchMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponseDto updateOrder(@Valid @RequestBody OrderUpdateDto dto, @PathVariable Long orderId) {
        return orderService.updateOrder(orderId, dto);
    }

    @PatchMapping("/{orderId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponseDto updateOrderStatus(@Valid @RequestBody OrderStatusUpdateDto dto,
                                              @PathVariable Long orderId) {
        return orderService.updateStatusOfOrder(orderId, dto);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
}
