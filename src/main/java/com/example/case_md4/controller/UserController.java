package com.example.case_md4.controller;

import com.example.case_md4.model.JwtResponse;
import com.example.case_md4.model.User;
import com.example.case_md4.service.user.IUserService;
import com.example.case_md4.service.user.JwtService;
import com.example.case_md4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;
    @GetMapping
    public ModelAndView showUser(){
        ModelAndView modelAndView = new ModelAndView("user/userList");
        modelAndView.addObject("user1", userService.findAll());
        return modelAndView;
    }
//@GetMapping
//public ResponseEntity<Iterable<User>> showAllUserValid() {
//    List<User> userList = (List<User>) userService.findAll();
//    if (userList.isEmpty()) {
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//    return new ResponseEntity<>(userList, HttpStatus.OK);
//}
@GetMapping("{id}")
public ResponseEntity<User> findByID(@PathVariable Long id) {
    User user = userService.findById(id).get();
    return new ResponseEntity<>(user, HttpStatus.OK);
}
@PutMapping
public ResponseEntity<User> update(@RequestBody User user) {
    userService.save(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
}

    @GetMapping("/userCreate")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/user/userCreate");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

//    @PostMapping("/user1")
//    public ModelAndView save(@RequestBody User user){
//        userService.save(user);
//        ModelAndView modelAndView= new ModelAndView("redirect:/user");
//        return modelAndView;
//    }
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtService.generateTokenLogin(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    User currentUser = userService.findByUsername(user.getName());
    return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername()));
}
    @PostMapping("/user1")
    public ResponseEntity<?> save(@RequestBody User user){
        userService.save(user);
//        ModelAndView modelAndView= new ModelAndView("redirect:/user");
        return ResponseEntity.ok("ok luoon");
    }
//
//    @GetMapping("/userEdit/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id){
//        ModelAndView modelAndView = new ModelAndView("/user/userEdit");
//        modelAndView.addObject("user1", userService.findById(id).get());
//        return modelAndView;
//    }
//    @PostMapping("/userEdit")
//    public ModelAndView update(User user){
//        userService.save(user);
//        ModelAndView modelAndView= new ModelAndView("redirect:/user");
//        return modelAndView;
//    }
//
//    @GetMapping("/delete/{id}")
//    public ModelAndView remove(@PathVariable Long id){
//        ModelAndView modelAndView= new ModelAndView("redirect:/user");
//        userService.remove(id);
//        return modelAndView;
//    }
}
