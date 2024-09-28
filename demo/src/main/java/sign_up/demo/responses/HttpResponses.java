package sign_up.demo.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HttpResponses {
    private String timeStamp;
    private int statusCode;
    private HttpStatus status;
    private String message;
    private String DeveloperMessage;
    private String path;
    private String requestMethod;
    private Map<?, ?> data;
}
