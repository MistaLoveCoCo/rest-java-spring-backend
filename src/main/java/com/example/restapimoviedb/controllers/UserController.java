package com.example.restapimoviedb.controllers;

import com.example.restapimoviedb.models.Customized;
import com.example.restapimoviedb.models.Users;
import com.example.restapimoviedb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@CrossOrigin("*")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity getAllUsers()
    {
        Customized c = new Customized("List of Users",userService.getAll());

        return new ResponseEntity(c, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getuserById(@PathVariable("id") String id)
    {
        Customized c = null;
        try {
            c= new Customized("Found the User", Collections.singletonList(userService.getUser(id)));
        } catch (Exception e) {
            c = new Customized("Not found the user",null);
            return new ResponseEntity(c, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(c,HttpStatus.OK);
    }

    @PostMapping(value = "/users",consumes = {

            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity insertNewUser(@RequestBody Users user)
    {
        userService.insertUser(user);
        Customized c = new Customized("The User data "+user.getFirstName() +" has been successfully added",null);
        return new ResponseEntity(c,HttpStatus.OK);
    }
}
