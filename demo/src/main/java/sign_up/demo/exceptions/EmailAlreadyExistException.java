package sign_up.demo.exceptions;

public class EmailAlreadyExistException extends DemoBaseException{
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
