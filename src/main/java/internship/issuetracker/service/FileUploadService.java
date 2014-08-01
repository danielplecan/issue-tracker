package internship.issuetracker.service;

import internship.issuetracker.entity.UploadedFile;
import internship.issuetracker.exception.FileUploadException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author dplecan
 */
@Service
public class FileUploadService {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public Long uploadFile(MultipartFile file) throws FileUploadException, IOException {
       String generatedName = UUID.randomUUID().toString();
       saveFile(file, generatedName);
       return persistFileEntity(file, generatedName);
    }
    
    private void saveFile(MultipartFile file, String generatedName) throws FileUploadException, IOException {
        if(file.isEmpty()) {
            throw new FileUploadException("Uploading has failed. The file was empty.");
        }
        
        String directoryPath = UploadedFile.LOCATION;
        File directory = new File(directoryPath);
        
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        File serverFile = new File(directory.getAbsolutePath() + File.separator + generatedName);
        file.transferTo(serverFile);
    }
    
    @Transactional
    private Long persistFileEntity(MultipartFile file, String generatedName) {
        UploadedFile uploadedFile = new UploadedFile();
        
        uploadedFile.setOriginalName(file.getOriginalFilename());
        uploadedFile.setTargetName(generatedName);
        uploadedFile.setMimeType(file.getContentType());
        uploadedFile.setSize(file.getSize());
        
        entityManager.persist(uploadedFile);
        
        return uploadedFile.getId();
    }
    
    @Transactional
    public boolean removeFile(Long fileId) {
        UploadedFile uploadedFile = entityManager.find(UploadedFile.class, fileId);
        if(uploadedFile == null) {
            return false;
        }
        
        File file = new File(UploadedFile.LOCATION + File.separator + uploadedFile.getTargetName());
        file.delete();
        
        entityManager.remove(uploadedFile);
        
        return true;
    }
    
    @Transactional
    public UploadedFile getUploadedFileEntity(Long fileId) {
        return entityManager.find(UploadedFile.class, fileId);
    }
    
    public File getFile(UploadedFile uploadedFile) {
        return new File(UploadedFile.LOCATION + File.separator + uploadedFile.getTargetName());
    }


    public List<UploadedFile> getAttachmentsByIds(List<Long> attachments) {
        List<UploadedFile> files = new ArrayList<>();
        for(Long attachment : attachments) {
            UploadedFile file = entityManager.find(UploadedFile.class, attachment);
            if(file != null) {
                files.add(file);
            }
        }
        
        return files;
    }
}
