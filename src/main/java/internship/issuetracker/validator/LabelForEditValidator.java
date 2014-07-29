/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.validator;

import internship.issuetracker.entity.Label;
import internship.issuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author atataru
 */
@Service
public class LabelForEditValidator implements Validator {

    @Autowired
    IssueService issueService;

    @Override
    public boolean supports(Class<?> type) {
        return Label.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Label label = (Label) o;

        if (issueService.getLabelById(label.getId()) == null) {
            errors.rejectValue("id", "idNotFound", "There is no label with the given id");
            return;
        }

        Label labelToCheck = issueService.getLabelByName(label.getName());
        if (labelToCheck != null && !labelToCheck.getId().equals(label.getId())) {
            errors.rejectValue("name", "labelNameExist", "A label with this name already exists.");
        }
    }
}
