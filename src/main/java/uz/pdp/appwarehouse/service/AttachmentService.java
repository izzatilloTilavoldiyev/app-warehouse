package uz.pdp.appwarehouse.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.dto.response.Response;

public interface AttachmentService {
    Response uploadFile(MultipartHttpServletRequest request);
}
