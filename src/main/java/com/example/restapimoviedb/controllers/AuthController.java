package com.example.restapimoviedb.controllers;

import com.example.restapimoviedb.models.Customized;
import com.example.restapimoviedb.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity userLogin(@RequestBody Users usr) {
        Customized c = null;
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usr.getUsername(), usr.getPassword()));
            Customized response = new Customized("You Have Logged in", null);
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (BadCredentialsException e) {
            Customized response = new Customized("Username or Password is Wrong", null);
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
    }

}
