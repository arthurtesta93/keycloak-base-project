package com.construcaosw.keycloak.controller;


import com.construcaosw.keycloak.dto.UserDataRequest;
import com.construcaosw.keycloak.dto.UserDataResponse;
import com.construcaosw.keycloak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    public final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDataResponse>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDataResponse> getUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDataRequest user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody UserDataRequest user, @PathVariable String userId){
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }

    @PutMapping("/{userId}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}
