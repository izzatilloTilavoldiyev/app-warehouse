package uz.pdp.appwarehouse.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.dto.response.Response;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.AttachmentContent;
import uz.pdp.appwarehouse.reqository.AttachmentContentRepository;
import uz.pdp.appwarehouse.reqository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    @Override
    public Response uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setMainContent(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Response("Successfully saved", true, savedAttachment.getId());
    }

    @Override
    public void getFile(Integer fileId, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(fileId);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(fileId);
            if (contentOptional.isPresent()) {
                AttachmentContent attachmentContent = contentOptional.get();

                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + attachment.getName() + "\"");

                response.setContentType(attachment.getContentType());

                FileCopyUtils.copy(attachmentContent.getMainContent(), response.getOutputStream());
            }
        }
    }


}
