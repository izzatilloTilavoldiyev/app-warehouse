package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.service.AttachmentService;

@RestController
@RequestMapping(value = "/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    //CREATE
    @PostMapping("/upload")
    public Response upload(MultipartHttpServletRequest request) {
        return attachmentService.uploadFile(request);
    }

    //READ
    //UPDATE
    //DELETE
}
