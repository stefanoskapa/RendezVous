package com.rendezvous.service;

import com.rendezvous.entity.User;
import com.rendezvous.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Stefanos
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(int userID) {
        return userRepository.findById(userID);
    }
}
