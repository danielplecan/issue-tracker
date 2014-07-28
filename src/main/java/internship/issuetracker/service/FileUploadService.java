package internship.issuetracker.service;

import internship.issuetracker.entity.UploadedFile;
import internship.issuetracker.exception.FileUploadException;
import java.io.File;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
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
       String generatedName = KeyGenerators.string().generateKey();
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
}
