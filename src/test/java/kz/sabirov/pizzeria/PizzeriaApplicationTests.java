package kz.sabirov.pizzeria;

import kz.sabirov.pizzeria.entities.Product;
import kz.sabirov.pizzeria.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PizzeriaApplicationTests {

    @Autowired
    private ProductService productService;
    @Test
    void contextLoads() {
    }

    @Test
    void findProducts(){
        List<Long> ids = new ArrayList<>();
        List<Product> initialProducts;
        ids.add(1L);
        ids.add(2L);
        initialProducts = productService.findProducts(ids);
        Product productOne = initialProducts.get(0);
        Product productTwo = initialProducts.get(1);
        Product checkProductOne = new Product();
        checkProductOne.setId(1L);
        checkProductOne.setPrice(50L);
        checkProductOne.setName("Tomato sauce");
        Product checkProductTwo = new Product();
        checkProductTwo.setId(2L);
        checkProductTwo.setPrice(100L);
        checkProductTwo.setName("Mozzarella");
        Assertions.assertEquals(productOne,checkProductOne);
        Assertions.assertEquals(productTwo, checkProductTwo);

    }
}
