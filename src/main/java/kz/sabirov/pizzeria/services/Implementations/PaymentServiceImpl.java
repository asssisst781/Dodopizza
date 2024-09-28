package kz.sabirov.pizzeria.services.Implementations;

import kz.sabirov.pizzeria.entities.PaymentMethod;
import kz.sabirov.pizzeria.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String checkPaymentMethod(PaymentMethod paymentMethod) {
        String msg;
        if(paymentMethod.getNumber().length() != 16) return msg = "Invalid length";
        if(!(paymentMethod.getDate().matches("^(0[1-9]|1[0-2])\\/(0[1-9]|[12][0-9]|3[01])$"))) return msg = "Invalid/expired date";
        if(!(paymentMethod.getCvv().matches("^\\d{3,4}$"))) return msg = "Invalid cvv";
        if(!(paymentMethod.getNumber().matches("^[45]\\d{15}$"))) return msg = "Unknown card";
        return null;
    }

    @Override
    public String determinePaymentMethod(PaymentMethod paymentMethod) {
        String msg;
        if (paymentMethod.getNumber().charAt(0) == '4'){
            return msg = "VISA";
        }else{
            return msg = "Master";
        }
    }
}
