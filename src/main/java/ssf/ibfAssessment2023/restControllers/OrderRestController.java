package ssf.ibfAssessment2023.restControllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import ssf.ibfAssessment2023.services.PizzaService;

// For Task 4

@RestController
@RequestMapping
public class OrderRestController {

    @Autowired
    private PizzaService pizzaSvc;

    @GetMapping(path = "/order/{orderId}")
    public ResponseEntity<String> getOrderIdAsJson(@PathVariable(name = "orderId") String orderId) {

        Optional<String> opt = pizzaSvc.getJsonStringById(orderId);

        if (opt.isEmpty()) {
            JsonObject obj = Json.createObjectBuilder()
                    .add("message", "order %s not found".formatted(orderId))
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj.toString());
        }

        return ResponseEntity.ok(opt.get());
    }
    
}
