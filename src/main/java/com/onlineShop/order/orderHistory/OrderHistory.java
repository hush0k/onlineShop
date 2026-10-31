package com.onlineShop.order.orderHistory;

import com.onlineShop.order.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name="order-histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order-history-seq")
    @SequenceGenerator(
            name = "order-history-seq",
            sequenceName = "order-history-seq-gen",
            allocationSize = 50
    )
    private Long id;

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="timestamp")
    private Instant timestamp;


    @PrePersist
    public void prePersist() {
        this.timestamp = Instant.now();
    }
}
