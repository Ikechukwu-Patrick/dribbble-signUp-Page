package sign_up.demo.service;

import sign_up.demo.requests.RegisterUserRequests;
import sign_up.demo.responses.RegisterUserResponses;

public interface UserService {
    RegisterUserResponses registerUser(RegisterUserRequests registerUserRequests);
    void validatePassWord(String PassWord);

}
