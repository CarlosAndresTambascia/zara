package com.between.zara.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "brand")
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Brand {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "brand_id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
}
