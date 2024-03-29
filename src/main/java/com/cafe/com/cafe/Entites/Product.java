package com.cafe.com.cafe.Entites;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;

// this query created in video - 7 
// p.category.id --> category is an object, category.id is its attribute video-6
@NamedQuery(name = "Product.getAllProduct", query = "select new com.cafe.com.cafe.wrapper.Product_Wrapper(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name) from Product p")
@NamedQuery(name = "Product.updateProductStatus", query = "update Product p set p.status=:status where p.id=:id")
@NamedQuery(name = "Product.getProductByCategory", query = "select new com.cafe.com.cafe.wrapper.Product_Wrapper(p.id, p.name) from Product p where p.category.id=:id and p.status='true'")
@NamedQuery(name = "Product.getProductById", query = "select new com.cafe.com.cafe.wrapper.Product_Wrapper(p.id,p.name,p.description,p.price) from Product p where p.id=:id")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "product")
public class Product implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    // many to one --> one row in a table is mapped to multiple rows in another table
    @ManyToOne(fetch = FetchType.LAZY) // lazy = fetch data when needed
    @JoinColumn(name = "category_fk", nullable = false) // connect to category table via foreign key
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status;
}
