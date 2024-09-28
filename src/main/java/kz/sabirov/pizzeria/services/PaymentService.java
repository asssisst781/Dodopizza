package kz.sabirov.pizzeria.services;

import kz.sabirov.pizzeria.entities.PaymentMethod;

public interface PaymentService {
    public String checkPaymentMethod(PaymentMethod paymentMethod);
    public String determinePaymentMethod(PaymentMethod paymentMethod);
}
