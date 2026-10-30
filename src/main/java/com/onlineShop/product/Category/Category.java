package com.onlineShop.product.Category;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="name", unique=true, nullable=false)
    private String name;

}
