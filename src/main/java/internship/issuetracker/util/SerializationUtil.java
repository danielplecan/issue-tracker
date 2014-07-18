package internship.issuetracker.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 *
 * @author inistor
 */
public class SerializationUtil {

    public static Map<String, Object> extractFieldErrors(BindingResult bindingResult) {
        Map<String, Object> fieldErrors = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return fieldErrors;
    }
}
