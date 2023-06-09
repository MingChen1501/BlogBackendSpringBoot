package org.mingchencodelab.blogbackendspringboot.controller;

import org.mingchencodelab.blogbackendspringboot.model.entity.User;
import org.mingchencodelab.blogbackendspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    /**
     * @param userService the user service
     */
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    /**
     * @return ResponseEntity<List<User>> all users
     */


    @GetMapping
    public ResponseEntity<Page<User>> getUsers(@PageableDefault(
            size = 20,
            sort = "id",
            direction = Direction.ASC) Pageable pageable) {
        // get all users with paging and sorting from service
        Page<User> users = userService.getAllUsers(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        //get user by id from service
        return userService.getUserById(id).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
