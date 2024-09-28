package kz.sabirov.pizzeria.services;

import kz.sabirov.pizzeria.entities.Product;
import kz.sabirov.pizzeria.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Product> findProducts(List<Long> ids);
}
