package internship.issuetracker.provider;

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
        System.out.println(username + " " + password);
        if(userService.loginUser(username, password)) {
            System.out.println("succccceeeees");
            Authentication confirmedAuthentication = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<GrantedAuthority>());
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
