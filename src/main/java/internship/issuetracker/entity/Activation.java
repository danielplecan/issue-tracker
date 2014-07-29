package internship.issuetracker.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

@NamedQuery(name = Activation.FIND_BY_ACTIVATION_HASH, query = "SELECT a from Activation a WHERE a.activationHash = :v_activationHash")

@Entity
@Table(name = "en_activation")
public class Activation implements Serializable {

    public static final String FIND_BY_ACTIVATION_HASH = "findByActivationHash";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_activation_id_seq", sequenceName = "en_activation_id_seq", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @Column(name = "activation_hash", length = 60, nullable = false)
    @NotBlank
    private String activationHash;

    @OneToOne
    @JoinColumn(name = "id_user")
    @Valid
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivationHash() {
        return activationHash;
    }

    public void setActivationHash(String activatiionHash) {
        this.activationHash = activatiionHash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}