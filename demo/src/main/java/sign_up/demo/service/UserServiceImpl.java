package sign_up.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import sign_up.demo.exceptions.DemoBaseException;
import sign_up.demo.exceptions.EmailAlreadyExistException;
import sign_up.demo.model.User;
import sign_up.demo.repository.UserRepository;
import sign_up.demo.requests.RegisterUserRequests;
import sign_up.demo.responses.RegisterUserResponses;

import java.util.regex.Pattern;

import static java.lang.Boolean.TRUE;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
    String EmailFormat = "A1b@2020";
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Validated
    public RegisterUserResponses registerUser(RegisterUserRequests registerUserRequests) {
        validateEmail(registerUserRequests.getEmail());
        validatePassWord(registerUserRequests.getPassword());
        validateUserName(registerUserRequests.getUsername());
        User user = modelMapper.map(registerUserRequests, User.class);
        user.setUserName(registerUserRequests.getUsername());
        user.setPassword(registerUserRequests.getPassword());
        user.setEmail(registerUserRequests.getEmail());
        user.setAgreedToTerms(TRUE);
        User savedUser = userRepository.save(user);
        RegisterUserResponses userResponses = modelMapper.map(savedUser, RegisterUserResponses.class);
        userResponses.setMessage("Signed up successfully");
        return userResponses;
    }

    @Override
    public void validatePassWord(String passWord) {
        if (passWord == null || passWord.trim().isEmpty()) {
            throw new DemoBaseException("Password cannot be null or empty");
        }
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);

        if (!pattern.matcher(passWord).matches()) {
            throw new DemoBaseException("Invalid password format.Something like  " + EmailFormat);
        }

    }


    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new DemoBaseException("Email cannot be null or empty");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(email).matches()) {
            throw new DemoBaseException("Invalid email format");
        }
        boolean emailExists = userRepository.existsByEmail(email);
        if (emailExists) {
            throw new EmailAlreadyExistException("Email " + email + "already exists");
        }

    }
    private void validateUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new DemoBaseException("Username cannot be null or empty");
        }

    }
}
