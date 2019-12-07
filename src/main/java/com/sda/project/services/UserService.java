package com.sda.project.services;

import com.sda.project.entities.User;
import com.sda.project.entities.UserStatus;
import com.sda.project.repositories.UsersRepository;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;

    public User createUser(User user) {
        user.setStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }

    public User validateUser(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (!optionalUser.isPresent()
                || !optionalUser.get().getPassword().equals(user.getPassword())
                || optionalUser.get().getStatus() != UserStatus.ACTIVE
        ) {
            return null;
        }

        return optionalUser.get();
    }

    public List<User> getActiveUsers() {
        return userRepository.findAllByStatus(UserStatus.ACTIVE);
    }

    public User deleteUser(User user) {
        user.setStatus(UserStatus.INACTIVE);
        return userRepository.save(user);
    }
}
