/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.dto;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.Label;
import java.util.List;
import javax.validation.Valid;
/**
 *
 * @author atataru
 */
public class NewIssueDTO {
    @Valid
    private Issue issue;
    
    private List<Long> labelIdList;

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public List<Long> getLabelIdList() {
        return labelIdList;
    }

    public void setLabelIdList(List<Long> labelIdList) {
        this.labelIdList = labelIdList;
    }
    
    
    
}
