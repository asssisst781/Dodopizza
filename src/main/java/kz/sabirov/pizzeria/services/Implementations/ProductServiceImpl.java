package kz.sabirov.pizzeria.services.Implementations;

import kz.sabirov.pizzeria.entities.Product;
import kz.sabirov.pizzeria.repositories.ProductRepository;
import kz.sabirov.pizzeria.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findProducts(List<Long> ids) {
        return productRepository.findAllById(ids);
    }
}
