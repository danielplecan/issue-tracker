package internship.issuetracker.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 *
 * @author dplecan
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

   @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);    
    	if (exception instanceof AuthenticationCredentialsNotFoundException) {
            if ("not activated".equals(exception.getMessage())) {
                response.getWriter().write("activation");
              
            } else {
                response.getWriter().write("credential");
            }
        }
    }
    
}
