package kz.sabirov.pizzeria.PDFGenerator;

import kz.sabirov.pizzeria.dto.OrderCustomDTO;
import kz.sabirov.pizzeria.dto.OrderDTO;
import kz.sabirov.pizzeria.entities.PaymentMethod;
import kz.sabirov.pizzeria.entities.Pizza;

public interface DocumentComp {
    void createPDF(String email, PaymentMethod pm, Pizza pizza, Long payment);
}
