package com.onlineShop.order.order;

import com.onlineShop.order.orderHistory.OrderStatus;
import com.onlineShop.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name="order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order-seq")
    @SequenceGenerator(
            name = "order-seq",
            sequenceName = "order-seq-gen",
            allocationSize = 50
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="predicted_date_of_delivery")
    private Instant predictedDateOfDelivery;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="subPrice")
    private BigDecimal subPrice;

    @Column(name="delivery_price")
    private BigDecimal deliveryPrice;

    @Column(name="discount")
    private BigDecimal discount;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Column(name="currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name="delivery_city")
    private String deliveryCity;

    @Column(name="delivery_address")
    private String deliveryAddress;


    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

}
