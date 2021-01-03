package com.food4good.controllers;

import com.food4good.database.entities.User;
import com.food4good.database.entities.UsersPreference;
import com.food4good.dto.NewOrderRequest;
import com.food4good.dto.NewOrderResponse;
import com.food4good.facad.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
public class UsersControllers {
    private final UsersService usersService;

    public UsersControllers(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public User userById(@PathVariable("userId") Long userId) throws Exception {
        return usersService.getById(userId);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Set<UsersPreference>> registerUser(@Validated @RequestBody String userToken) throws Exception {
        return ResponseEntity.ok(usersService.registerNewUser(userToken));
    }
}
