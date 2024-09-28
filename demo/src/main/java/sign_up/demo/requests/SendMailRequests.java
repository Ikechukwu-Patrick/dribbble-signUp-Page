package sign_up.demo.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendMailRequests {
    private String subject;
    private String message;
}
