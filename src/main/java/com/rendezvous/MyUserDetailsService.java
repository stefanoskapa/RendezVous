
package com.rendezvous;

import com.rendezvous.model.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    /*
    Here typically we call JPA methods to create a UserDetails object
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <User> user = userRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("User " + username + " not found!"));
        return user.map(MyUserDetails::new).get();
    }

}
