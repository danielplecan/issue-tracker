/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.validator;

import internship.issuetracker.entity.Comment;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author atataru
 */
public class CommentValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Comment.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Comment comment = (Comment) o;
        if(comment.getChangeState() != null) {
            return;
        } 
        if(comment.getContent() == null) {
            errors.rejectValue("content", "commentSize", "A simple comment must not be blank.");
            return;
        }
        int contentSize = comment.getContent().length();
        if( contentSize <3 || contentSize > 500) {
            errors.rejectValue("content", "commentSize", "A comment must contain between 3 and 500 characters.");
        }
    }

}
