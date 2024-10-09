package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name="cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="vacation_id",nullable = false)
    private Vacation vacation;
    //changed
    @ManyToMany (fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "cartitems")
    Set<Excursion> excursions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;

    @Column(name="create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name="last_update")
    @UpdateTimestamp
    private Date last_update;


}
