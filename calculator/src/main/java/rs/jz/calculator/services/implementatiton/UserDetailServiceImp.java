package rs.jz.calculator.services.implementatiton;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.jz.calculator.model.MyUser;
import rs.jz.calculator.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepository.findByUsername(username);
        if(user == null) {
            return null;
        }
        return new User(username, user.getPassword(), getGrantedAuthorities(user));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(MyUser user) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if(user.getUserType().toString().equals("ADMIN")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthorities;
    }
}
