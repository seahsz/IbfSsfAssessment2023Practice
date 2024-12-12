package ssf.ibfAssessment2023.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PizzaOrder {

    @NotBlank(message = "Pizza Selection cannot be empty")
    private String pizza;

    @NotBlank(message = "Pizza size cannot be empty")
    private String size;

    @Min(value = 1, message = "Minimum order quantity is 1")
    @Max(value = 10, message = "Maximum order quantity is 10")
    @NotNull(message = "Quantity cannot be empty")
    private Integer quantity;

    public String getPizza() { return pizza; }
    public void setPizza(String pizza) { this.pizza = pizza; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
}
