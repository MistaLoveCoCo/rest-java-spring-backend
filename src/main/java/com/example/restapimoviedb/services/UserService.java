package com.example.restapimoviedb.services;

import com.example.restapimoviedb.models.UserRepository;
import com.example.restapimoviedb.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void insertUser(Users user)
    {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.insert(user);
    }

    public Optional<Users> getUser(String id) throws Exception {
        Optional<Users> findUser = userRepository.findById(id);
        if(!findUser.isPresent())
        {
            throw new Exception ("User with id "+id+" not found!");
        }
        return findUser;
    }

    public List<Users> getAll()
    {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {

        Users founduser = userRepository.findByUsername(uname);

        String username = founduser.getUsername();
        String password = founduser.getPassword();

        return new User(username,password, new ArrayList<>());
    }

}
