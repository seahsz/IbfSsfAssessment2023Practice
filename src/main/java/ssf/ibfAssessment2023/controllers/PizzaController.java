package ssf.ibfAssessment2023.controllers;

import static ssf.ibfAssessment2023.common.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ssf.ibfAssessment2023.models.DeliveryDetails;
import ssf.ibfAssessment2023.models.OrderConfirmation;
import ssf.ibfAssessment2023.models.PizzaOrder;
import ssf.ibfAssessment2023.services.PizzaService;

@Controller
@RequestMapping
public class PizzaController {

    @Autowired
    private PizzaService pizzaSvc;

    // Task 2
    // POST /pizza
    // Content-Type: application/x-www-form-urlencoded
    @PostMapping(path = "/pizza", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postPizza(
            @Valid @ModelAttribute(name = TH_PIZZA_ORDER) PizzaOrder pizzaOrder,
            BindingResult binding,
            Model model,
            HttpSession session) {

        String selectedPizza = pizzaOrder.getPizza();
        String selectedSize = pizzaOrder.getSize();

        // Not null, not blank --> check if value is expected. If unexpected --> add binding error
        if (selectedPizza != null && !selectedPizza.isBlank() && !PIZZA_SELECTION.contains(selectedPizza)) {
            binding.addError(
                    new FieldError("pizzaOrder", "pizza", "Unexpected value provided for pizza"));
        }

        // Not null, not blank --> check if value is expected. If unexpected --> add binding error
        if (selectedSize != null && !selectedSize.isBlank() && !PIZZA_SIZE.contains(selectedSize)) {
            binding.addError(
                new FieldError("pizzaOrder", "size", "Unexpected value provided for size"));
        }

        // Return all binding errors (if there are any)
        if (binding.hasErrors())
            return "index";

        // If not, 1) Save order details to session and 2) Display View 1 (Delivery Details Form)
        session.setAttribute(SESSION_PIZZA_ORDER, pizzaOrder);

        model.addAttribute(TH_DELIVERY_DETAILS, new DeliveryDetails());
        return "deliveryDetails";
    }

    // Task 3
    // POST /pizza/order
    // Content-Type: application/x-www-form-urlencoded
    @PostMapping(path = "/pizza/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postPizzaOrder(
        @Valid @ModelAttribute(name = TH_DELIVERY_DETAILS) DeliveryDetails deliveryDetails,
        BindingResult binding,
        HttpSession session,
        Model model
    ) {

        if(binding.hasErrors())
            return "deliveryDetails";

        // Retrieve the pizza order from session + pass both pizza order & delivery details to Svc Layer
        OrderConfirmation orderConfirm = pizzaSvc.persistOrder((PizzaOrder) session.getAttribute(SESSION_PIZZA_ORDER), deliveryDetails);

        model.addAttribute(TH_ORDER_CONFIRMATION, orderConfirm);

        return "orderConfirmation";
    }


}
