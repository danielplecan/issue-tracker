package internship.issuetracker.entity;

import java.io.File;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author dplecan
 */
@Entity
@Table(name = "en_uploads")
public class UploadedFile implements Serializable {
    public static final String LOCATION = System.getProperty("catalina.home") + File.separator + "uploads";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_uploads_id_seq", sequenceName = "en_uploads_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "original_name", nullable = false)
    @NotBlank
    private String originalName;
    
    @Column(name = "target_name", nullable = false)
    @NotBlank
    private String targetName;
    
    
    @Column(name = "mime_type", nullable = false)
    @NotBlank
    private String mimeType;
    
    @Column(name = "file_size", nullable = false)
    private Long size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
