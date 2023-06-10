package uz.pdp.appwarehouse.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.dto.response.Response;

import java.io.IOException;

public interface AttachmentService {
    Response uploadFile(MultipartHttpServletRequest request);

    void getFile(Integer fileId, HttpServletResponse response) throws IOException;
}
