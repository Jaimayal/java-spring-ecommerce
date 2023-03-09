package com.jaimayal.tarvinshop.categories.entity;

import com.jaimayal.tarvinshop.products.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @ManyToMany
    @JoinTable(
            name = "t_categories_products", 
            joinColumns = @JoinColumn(
                    name = "category_id", referencedColumnName = "id"
            ), 
            inverseJoinColumns = @JoinColumn(
                    name = "product_id", referencedColumnName = "id"
            )
    )
    private List<Product> products;
    
    
    public void addProduct(final Product product) {
        products.add(product);
    }
    
    public void addProducts(final Collection<Product> products) {
        this.products.addAll(products);
    }
}
