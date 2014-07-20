package internship.issuetracker.provider;

import internship.issuetracker.entity.User;
import internship.issuetracker.service.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author dplecan
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        User authenticatedUser;
        if((authenticatedUser = userService.loginUser(username, password)) != null) {
            Authentication confirmedAuthentication = new UsernamePasswordAuthenticationToken(authenticatedUser, password, new ArrayList<GrantedAuthority>());
            return confirmedAuthentication;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
