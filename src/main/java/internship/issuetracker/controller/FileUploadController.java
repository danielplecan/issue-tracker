package internship.issuetracker.controller;

import internship.issuetracker.exception.FileUploadException;
import internship.issuetracker.service.FileUploadService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        } finally {
            return responseMap;
        }
    }
}
