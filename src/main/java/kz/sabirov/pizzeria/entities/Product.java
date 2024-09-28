package kz.sabirov.pizzeria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;
    @ManyToMany(mappedBy = "products")
    private List<Pizza> pizzas;

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Product)) return false;
        Product product = (Product) o;
        if(this.name != null ? !(this.name.equals(product.getName())) : product.getName() != null) return false;
        return this.price != null ? this.price.equals(product.getPrice()) : product.getPrice() == null;
    }
}
