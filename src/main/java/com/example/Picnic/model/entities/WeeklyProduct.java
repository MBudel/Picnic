package com.example.Picnic.model.entities;

import javax.persistence.*;

@Entity(name = "WeeklyProduct")
public class WeeklyProduct {

    @Id
    @SequenceGenerator(
            name = "weeklyproduct_sequence",
            sequenceName = "weeklyproduct_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "weeklyproduct_sequence"
    )
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "ProductId", nullable = false)
    private String productId;

    @Column(name = "Count", nullable = false)
    private int count;

    @ManyToOne()
    @JoinColumn(name = "PicnicUserId", nullable = false)
    private PicnicUser picnicUser;

    public WeeklyProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PicnicUser getPicnicUser() {
        return picnicUser;
    }

    public void setPicnicUser(PicnicUser picnicUser) {
        this.picnicUser = picnicUser;
    }
}
