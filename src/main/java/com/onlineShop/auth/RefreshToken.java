package com.onlineShop.auth;

import com.onlineShop.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name="refresh_token")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_refresh_token")
    @SequenceGenerator(
            name = "seq_refresh_token",
            sequenceName = "seq_refresh_token",
            allocationSize = 50
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "token_hash", nullable = false, unique = true)
    private String tokenHash;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "revoked", nullable = false)
    private boolean revoked;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;


    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
        if (expiresAt == null) expiresAt = createdAt.plus(Duration.ofDays(30));
    }

}
