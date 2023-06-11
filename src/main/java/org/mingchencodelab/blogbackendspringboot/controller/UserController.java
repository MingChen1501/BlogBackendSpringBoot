package org.mingchencodelab.blogbackendspringboot.controller;

import org.mingchencodelab.blogbackendspringboot.model.dto.UserDto;
import org.mingchencodelab.blogbackendspringboot.model.entity.User;
import org.mingchencodelab.blogbackendspringboot.model.enumeration.Role;
import org.mingchencodelab.blogbackendspringboot.model.payload.request.CreateUserRequest;
import org.mingchencodelab.blogbackendspringboot.model.payload.response.CreateUserResponse;
import org.mingchencodelab.blogbackendspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

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

    /**
     * @param id the user id
     * @return ResponseEntity<User> the user with the id
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        //get user by id from service
        return userService.getUserById(id).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * @param id the user id
     * @return ResponseEntity<?> with HttpStatus.NO_CONTENT if the user is deleted
     * or HttpStatus.NOT_FOUND if the user is not found
     * or HttpStatus.BAD_REQUEST if request is bad
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        //delete user by id from service
        try {
            if (userService.deleteUserById(id)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //create user by id from service with UserDto
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest userRequest,
                                        UriComponentsBuilder uriComponentsBuilder) {
        //mapping userRequest to userDto
        UserDto userDto = UserDto.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .role(Role.valueOf(userRequest.getRole()))
                .fullName(userRequest.getFullName())
                .build();
        //create uriBuilders.path for user id
        try {
            Optional<UserDto> userDtoCreated = userService.createUser(userDto);
            if (userDtoCreated.isPresent()) {
                UserDto userDtoCreatedValue = userDtoCreated.get();
                //create uriBuilders.path for user id
                UriComponents uriComponents = uriComponentsBuilder
                        .path("/api/v1/users/{id}")
                        .buildAndExpand(userDtoCreated.get().getId());
                String userUri = uriComponents.toUriString();
                return ResponseEntity.status(HttpStatus.CREATED).header("Location", userUri).build();
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
