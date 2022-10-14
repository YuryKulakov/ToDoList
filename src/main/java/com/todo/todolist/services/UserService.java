package com.todo.todolist.services;

import com.todo.todolist.models.UserRepresentation;
import com.todo.todolist.persistance.entity.User;
import com.todo.todolist.persistance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void createUser(UserRepresentation userRepresent){
        User user = new User();
        user.setUsername(userRepresent.getUsername());
        user.setPassword(passwordEncoder.encode(userRepresent.getPassword()));
        userRepository.save(user);
    }

    public Optional<Long> getCurrentUserId(){
        Optional<String> currentUser = getCurrentUser();
        if(currentUser.isPresent()){
            return userRepository.getUserByUsername(currentUser.get()).map(User::getId);
        }
        return Optional.empty();
    }

    public static Optional<String> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return Optional.of(authentication.getName());
        }
        return Optional.empty();
    }
}
