package ru.geekbrains.entities;

import ru.geekbrains.services.ProductDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "from Product"),
        @NamedQuery(name = "countAllProducts", query = "select count(*) from Product"),
        @NamedQuery(name = "deleteByIdProduct", query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "findByNameProduct", query = "from Product p where p.name = :name"),
        @NamedQuery(name = "findByCategoryId", query = "from Product p where p.category.id = :category_id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 1024)
    private String description;

    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, String description, BigDecimal price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }


    public Product(ProductDto productDto, Category category){
        this(productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getPrice(), category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
