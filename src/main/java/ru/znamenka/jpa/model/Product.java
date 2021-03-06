package ru.znamenka.jpa.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "JF_products")
public class Product implements BaseModel<Long> {

    @Id
    @Column(name = "product_id")
    @Getter @Setter
    private Long id;

    @Column(name = "product_name", nullable = false)
    @Getter @Setter
    @NonNull
    private String productName;

    @Column(name = "expire_days")
    @Getter @Setter
    private Integer expireDays;

    @Column(name = "price", nullable = false)
    @Getter @Setter
    @NonNull
    private Double price;

    @OneToMany(mappedBy = "product", fetch = LAZY)
    @Getter @Setter
    private List<Purchase> purchases;

}
