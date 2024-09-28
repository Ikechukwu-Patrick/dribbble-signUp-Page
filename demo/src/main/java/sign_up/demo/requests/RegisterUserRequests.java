package sign_up.demo.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserRequests {
    @NotBlank(message = "UserName is required")
    private String username;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @NotBlank(message = "Verify Password is required")
    private boolean isAgreedToTerms;

}
