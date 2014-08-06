/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

@NamedQuery(name = RecoverPassword.FIND_BY_RECOVERY_HASH, query = "SELECT r from RecoverPassword r WHERE r.recoverPasswordHash = :v_recoverPasswordHash")

@Entity
@Table(name = "en_recovery")
public class RecoverPassword implements Serializable {
    
     public static final String FIND_BY_RECOVERY_HASH = "findByRecoveryHash";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_recovery_id_seq", sequenceName = "en_recovery_id_seq", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @Column(name = "recover_password_hash", length = 60, nullable = false)
    @NotBlank
    private String recoverPasswordHash;

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

    public String getRecoverPasswordHash() {
        return recoverPasswordHash;
    }

    public void setRecoverPasswordHash(String recoverPasswordHash) {
        this.recoverPasswordHash = recoverPasswordHash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
