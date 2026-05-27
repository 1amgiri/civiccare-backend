import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateEmergencyServiceRequest {

    @NotBlank(message = "Service name is required")
    private String name;

    @Pattern(regexp = "^[0-9]{3}$", message = "Phone must be 3 digits")
    private String phone;

    @NotBlank(message = "City is required")
    private String city;

    private boolean verified;

    // getters
}
