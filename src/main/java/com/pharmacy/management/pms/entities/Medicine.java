package com.pharmacy.management.pms.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medicine")
public class Medicine extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost_price")
    private String costPrice;

    @Column(name = "selling_price")
    private String sellingPrice;

    @Column(name = "side_effect")
    private String sideEffect;

    @Column(name = "how_to_use")
    private String howToUse;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @Column(name = "minimum_stock_level")
    private int minimumStockLevel;

    @Column(name = "maximum_stock_level")
    private int maximumStockLevel;

    @Column(name = "manufactured_date", nullable = true)
    private Date manufacturedDate;

    @Column(name = "expiration_date", nullable = true)
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
