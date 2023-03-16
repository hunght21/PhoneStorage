package com.ltnc.phonestorage.entity;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partNumberId;


    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @NotBlank
    @Size(min = 3 ,max = 60)
    @Column(name = "part_description", nullable = false)
    private String partDescription;

    @Min(value = 0)
    @Column(name = "part_qty", nullable = false)
    private Integer partQty;

    @Min(value = 0)
    @Column(name = "part_cost", nullable = false)
    private Double partCost;

    @Min(value = 0)
    @Column(name = "part_retail_price", nullable = false)
    private Double partRetailPrice;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Transient
    private Integer brandId;

    @Builder.Default
    @ManyToMany(mappedBy = "listOfProducts")
    private List<Order> orders = new ArrayList<>();

    @Override
    public String toString() {
        return partDescription;
    }

}