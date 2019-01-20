package com.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.model.ApiResponse;
import com.myapp.model.LoginUser;
import com.myapp.model.User;
import com.myapp.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse<String> validateLoginUser(@RequestBody LoginUser loginUser){
    	final User user = userService.findOne(loginUser.getUsername());
    	if(user!=null) {
    		if(user.getPassword().equals(loginUser.getPassword()))
    			return new ApiResponse<>(HttpStatus.OK.value(),"User logged successfully",loginUser.getUsername());
    	}
    	return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Invalid username or password",null);
    }
    
    @PostMapping
    public ApiResponse<User> saveUser(@RequestBody User user){
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
    }

    @GetMapping
    public ApiResponse<List<User>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.",userService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<User> update(@RequestBody User user) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(user));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", null);
    }

}