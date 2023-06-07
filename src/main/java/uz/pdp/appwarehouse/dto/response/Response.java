package uz.pdp.appwarehouse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message;
    private boolean result;
    private Object object;

    public Response(String message, boolean result) {
        this.message = message;
        this.result = result;
    }
}
