package com.todo.todolist.configs;

import com.todo.todolist.persistance.entity.User;
import com.todo.todolist.persistance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class UserAuthService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.getUserByUsername(username);
        if(!optional.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(optional.get().getUsername(),
                optional.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
