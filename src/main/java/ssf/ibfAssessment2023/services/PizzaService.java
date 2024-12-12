package ssf.ibfAssessment2023.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import ssf.ibfAssessment2023.models.DeliveryDetails;
import ssf.ibfAssessment2023.models.OrderConfirmation;
import ssf.ibfAssessment2023.models.PizzaOrder;
import ssf.ibfAssessment2023.repositories.PizzaRepository;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepo;

    // For Task 4
    public Optional<String> getJsonStringById(String orderId) {
        return pizzaRepo.getJsonStringById(orderId);
    }

    public OrderConfirmation persistOrder(PizzaOrder pizzaOrder, DeliveryDetails deliveryDetails) {
        String orderId = generateOrderId();

        double totalCost = calculateOrderCost(pizzaOrder, deliveryDetails);

        String orderAsJsonString = convertOrderToJsonString(orderId, totalCost, pizzaOrder, deliveryDetails);
        
        pizzaRepo.saveOrderAsJsonString(orderId, orderAsJsonString);

        // (condition) ? (value if true) : (value if false)
        double costExcludeRush = deliveryDetails.isRush() ? totalCost - 2 : totalCost;

        return new OrderConfirmation(orderId,
                                     deliveryDetails.getAddress(),
                                     deliveryDetails.isRush(), 
                                     costExcludeRush, 
                                     totalCost);
    }

    // Helper function: Generate UUID for Order ID
    private String generateOrderId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    // Helper function: Calculate order cost
    private double calculateOrderCost(PizzaOrder pizzaOrder, DeliveryDetails deliveryDetails) {

        int basePizzaCost = 0;

        switch (pizzaOrder.getPizza()) {
            case "bella":
                basePizzaCost = 30;
                break;

            case "marinara":
                basePizzaCost = 30;
                break;

            case "spianatacalabrese":
                basePizzaCost = 30;
                break;

            case "margherita":
                basePizzaCost = 22;
                break;

            case "trioformaggio":
                basePizzaCost = 25;
                break;
        }

        double pizzaSizeMultiplier = 0;

        switch (pizzaOrder.getSize()) {
            case "sm":
                pizzaSizeMultiplier = 1;
                break;

            case "md":
                pizzaSizeMultiplier = 1.2;
                break;

            case "lg":
                pizzaSizeMultiplier = 1.5;
                break;
        }

        double subTotal = basePizzaCost * pizzaSizeMultiplier * pizzaOrder.getQuantity();

        if (deliveryDetails.isRush())
            return subTotal + 2;

        else {
            return subTotal;
        }
    }

    // Helper function: Convert Order Details into Json String
    private String convertOrderToJsonString(String orderId, double totalCost, PizzaOrder pizzaOrder,
            DeliveryDetails deliveryDetails) {

        JsonObject jsonObj = Json.createObjectBuilder()
                .add("orderId", orderId)
                .add("name", deliveryDetails.getName())
                .add("address", deliveryDetails.getAddress())
                .add("rush", deliveryDetails.isRush())
                .add("comments", deliveryDetails.getComments())
                .add("pizza", pizzaOrder.getPizza())
                .add("size", pizzaOrder.getSize())
                .add("quantity", pizzaOrder.getQuantity())
                .add("total", totalCost)
                .build();

        return jsonObj.toString();
    }

}
