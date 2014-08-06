package internship.issuetracker.controller;

import internship.issuetracker.dto.OrphanAttachmentDTO;
import internship.issuetracker.entity.UploadedFile;
import internship.issuetracker.exception.FileUploadException;
import internship.issuetracker.service.FileUploadService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author dplecan
 */
@Controller
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String getUploadPage() {
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, Object> responseMap = new HashMap<>();
        
        try {
            Long fileId = fileUploadService.uploadFile(file);
            responseMap.put("success", true);
            responseMap.put("fileId", fileId);
        } catch (IOException | FileUploadException exception) {
            responseMap.put("success", false);
            responseMap.put("error", exception.getMessage());
        }
        
        return responseMap;
    }
    
    @RequestMapping(value = "/attachment/remove/{fileId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> removeFile(@PathVariable("fileId") Long fileId) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", fileUploadService.removeFile(fileId));
        return responseMap;
    }
    
    @RequestMapping(value = "/attachment/download/{fileId}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, @PathVariable("fileId") Long fileId) throws FileNotFoundException {
        UploadedFile uploadedFile = fileUploadService.getUploadedFileEntity(fileId);
        File file = fileUploadService.getFile(uploadedFile);
        
        try(InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            response.setContentType(uploadedFile.getMimeType());
            response.setHeader("Content-disposition", "attachment; filename=\"" + uploadedFile.getOriginalName() + "\"");
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException exception) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/attachment/remove-orphans", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> removeOrphanAttachments(@RequestBody OrphanAttachmentDTO orphanAttachment) {
        Map<String, Object> responseMap = new HashMap<>();
        fileUploadService.removeOrphanAttachments(orphanAttachment.getAttachments());
        responseMap.put("success", true);
        return responseMap;
    }
}
