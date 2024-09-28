package sign_up.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sign_up.demo.exceptions.DemoBaseException;
import sign_up.demo.requests.RegisterUserRequests;
import sign_up.demo.responses.HttpResponses;
import sign_up.demo.responses.RegisterUserResponses;
import sign_up.demo.service.UserService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/signup")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<HttpResponses> createUser(@RequestBody RegisterUserRequests request) {
        try {
            RegisterUserResponses newUser = userService.registerUser(request);
            return ResponseEntity.created(URI.create("/api/users/" + newUser)).body(
                    HttpResponses.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .data(Map.of("user", newUser))
                            .message("User created")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );
        } catch (DemoBaseException e) {
            return ResponseEntity.badRequest().body(
                    HttpResponses.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .message("Error: " + e.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponses.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .message("Unexpected error occurred: " + e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .build());
        }
    }


}
