package com.jaimayal.tarvinshop.orders.entity;

import com.jaimayal.tarvinshop.AuthSystem.entity.User;
import com.jaimayal.tarvinshop.products.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "t_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToMany
    @JoinTable(
            name = "t_orders_products", 
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id", referencedColumnName = "id"
            )
        
    )
    private List<Product> products;
    
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User user;
}
