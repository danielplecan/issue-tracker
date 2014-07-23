/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.validator;

import internship.issuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import internship.issuetracker.entity.Label;

/**
 *
 * @author atataru
 */
@Service
public class LabelValidator implements Validator{

    @Autowired
    IssueService issueService;
    
    @Override
    public boolean supports(Class<?> type) {
        return Label.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Label label = (Label)o;
        
        if(issueService.labelExists(label.getName())) {
            errors.rejectValue("name", "labelNameExist", "A label with this name already exists.");
        }
    }
    
}
