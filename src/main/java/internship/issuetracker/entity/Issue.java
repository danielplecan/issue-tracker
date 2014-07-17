/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author atataru
 */

@NamedQueries({
    @NamedQuery(name = Issue.FIND_ALL, query = "SELECT u from Issue u")
})


@Entity
@Table(name = "en_issues")
public class Issue implements Serializable {
    public static final String FIND_ALL = "findAllIssues";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_issues_id_seq", sequenceName = "en_issues_id_seq", allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(length = 100, name ="issue_title")
    @NotNull
    @NotBlank(message = "An issue must have a title.")
    private String title;
    
    @Basic(optional = true)
    @Column(name = "issue_content")
    private String content;
    
    @Basic(optional = false)
    @Column(name = "issue_state")
    private IssueState state;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_date", length = 100)
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "id_owner")
    @NotNull
    private User owner;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public IssueState getState() {
        return state;
    }

    public void setState(IssueState state) {
        this.state = state;
    } 

}
