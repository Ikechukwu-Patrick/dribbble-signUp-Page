package sign_up.demo.sevices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sign_up.demo.requests.RegisterUserRequests;
import sign_up.demo.responses.RegisterUserResponses;
import sign_up.demo.service.UserService;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("test that user sign up successfully")
    public void registerTest() {
        RegisterUserRequests request = new RegisterUserRequests();
        request.setEmail("ike20853@gmail.com");
        request.setUsername("Ikenna");
        request.setPassword("111555Pat@1@gmail");
        request.setAgreedToTerms(TRUE);
        RegisterUserResponses response = userService.registerUser(request);
        assertTrue(response.getMessage().contains("Signed up successfully"));





    }

}

