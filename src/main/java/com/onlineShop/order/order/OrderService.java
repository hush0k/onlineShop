package com.onlineShop.order.order;

import com.onlineShop.exception.order.HandleOrderAlreadyCompleted;
import com.onlineShop.exception.order.HandleOrderNotFound;
import com.onlineShop.order.order.dto.request.OrderStatusUpdateDto;
import com.onlineShop.order.order.dto.request.OrderUpdateDto;
import com.onlineShop.order.order.dto.response.OrderResponseDto;
import com.onlineShop.order.orderHistory.OrderHistory;
import com.onlineShop.order.orderHistory.OrderHistroyRepository;
import com.onlineShop.order.orderHistory.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderHistroyRepository orderHistoryRepository;
    private final OrderMapper orderMapper;

    private Order orderIsExists(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new HandleOrderNotFound(orderId)
        );
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrder(Long orderId) {
        return orderMapper.toOrderResponseDto(orderIsExists(orderId));
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(orderMapper::toOrderResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getMyOrders(String email, Pageable pageable) {
        return orderRepository.findAllByUserEmail(email, pageable).map(orderMapper::toOrderResponseDto);
    }

    public OrderResponseDto updateOrder(Long orderId, OrderUpdateDto dto) {
        Order order = orderIsExists(orderId);
        orderMapper.updateOrderFromDto(dto, order);
        orderRepository.flush();
        return orderMapper.toOrderResponseDto(order);
    }

    public OrderResponseDto updateStatusOfOrder(Long orderId, OrderStatusUpdateDto dto) {
        Order order = orderIsExists(orderId);
        OrderStatus current = order.getStatus();

        if (!current.canTransitionTo(dto.status())) {
            throw new HandleOrderAlreadyCompleted(current, dto.status());
        }

        order.setStatus(dto.status());
        orderHistoryRepository.save(
                OrderHistory.builder()
                        .order(order)
                        .status(dto.status())
                        .build()
        );
        return orderMapper.toOrderResponseDto(order);
    }

    public void deleteOrder(Long orderId) {
        Order order = orderIsExists(orderId);
        orderHistoryRepository.deleteAllByOrder(order);
        orderRepository.delete(order);
    }

    @Transactional(readOnly = true)
    public boolean isOwner(Long orderId, String email) {
        return orderRepository.existsByIdAndUserEmail(orderId, email);
    }
}
