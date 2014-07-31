package internship.issuetracker.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author scalin
 */
@NamedQueries({
    @NamedQuery(name = Label.FIND_ALL_LABELS, query = "SELECT u from Label u ORDER BY u.name"),
    @NamedQuery(name = Label.FIND_LABEL_BY_NAME, query = "SELECT u from Label u WHERE u.name = :label_name")
})

@Entity
@Table(name = "en_labels")
public class Label implements Serializable {

    public static final String FIND_ALL_LABELS = "findAllLabels";
    public static final String FIND_LABEL_BY_NAME = "findLabelByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_label_id_seq", sequenceName = "en_label_id_seq", allocationSize = 1)
    private Long id;

    @Basic(optional = false)
    @Column(name = "label_name", length = 15)
    @NotBlank(message = "Label name cannot contain only whitespaces.")
    @Size(min = 1, max = 15, message = "A label name must contain between 3 and 15 characters")
    private String name;

    @Basic(optional = false)
    @NotNull(message = "A label must have a color ")
    @NotBlank(message = "A label must have a color ")
    @Column(name = "label_color")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Not a valid color")
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
