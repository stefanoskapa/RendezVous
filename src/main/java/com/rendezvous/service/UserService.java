package com.rendezvous.service;

import com.rendezvous.entity.User;
import com.rendezvous.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Integer userID) {
        Optional<User> user = userRepository.findById(userID);
        user.orElseThrow(() -> new UsernameNotFoundException("User " + userID + " not found!"));
        return user.get();
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found!"));
        return user.get();
    }
}
