package uz.pdp.appwarehouse.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.service.AttachmentService;

import java.io.IOException;

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

    @GetMapping("/getFile/{id}")
    public void getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.getFile(id, response);
    }
}
