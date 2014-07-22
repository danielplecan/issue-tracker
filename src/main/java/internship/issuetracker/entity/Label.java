package internship.issuetracker.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author scalin
 */
@NamedQueries({
    @NamedQuery(name = Label.FIND_ALL_LABELS, query = "SELECT u from Label u")
})


@Entity
@Table(name = "en_labels")
public class Label implements Serializable{
    
    public static final String FIND_ALL_LABELS = "findAllLabels";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_label_id_seq", sequenceName = "en_label_id_seq", allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "label_name")
    @Size( min = 1, max = 15 )
    private String name;
    
    @Basic(optional = false)
    @Column(name = "label_color")
    @Pattern(regexp ="^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",  message = "Not a valid color")
    private String color;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
