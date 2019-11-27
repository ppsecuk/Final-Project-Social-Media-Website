package com.sda.project.services;

import com.sda.project.entities.User;
import com.sda.project.entities.UserStatus;
import com.sda.project.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;

    public User createUser(User user) {
        user.setStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }

    public User validateUser(User user){
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if(!optionalUser.isPresent()
                || !optionalUser.get().getPassword().equals(user.getPassword())) {
            return  null;
        }

        return  optionalUser.get();
    }
}
