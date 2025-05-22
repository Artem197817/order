package com.artema.order.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_files")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String contentType;

    @Lob
    @Column(nullable = false)
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;
}

