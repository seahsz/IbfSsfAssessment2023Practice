package ssf.ibfAssessment2023.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DeliveryDetails {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "[0-9]{8}", message = "Phone number must be 8 digits")
    private String phoneNumber;

    private boolean rush;

    private String comments;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public boolean isRush() { return rush; }
    public void setRush(boolean rush) { this.rush = rush; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    
    
}
