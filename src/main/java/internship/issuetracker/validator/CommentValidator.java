/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.validator;

import internship.issuetracker.dto.CommentDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author atataru
 */
public class CommentValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return CommentDTO.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CommentDTO comment = (CommentDTO) o;
        int contentSize = comment.getContent().length();
        if (comment.getChangeState() == null) {
            if (contentSize < 3 || contentSize > 500) {
                errors.rejectValue("content", "commentSize", "A comment must contain between 3 and 500 characters.");
            }
        } else {
            if (contentSize > 0 && contentSize <3) {
                errors.rejectValue("content", "commentSize", "A comment must contain between 3 and 500 characters.");
            } else if (contentSize > 500) {
                errors.rejectValue("content", "commentSize", "A comment must contain between 3 and 500 characters.");
            }
        }
    }
}
