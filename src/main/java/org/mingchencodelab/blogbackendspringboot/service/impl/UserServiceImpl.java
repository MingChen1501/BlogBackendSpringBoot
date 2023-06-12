package org.mingchencodelab.blogbackendspringboot.service.impl;

import org.mingchencodelab.blogbackendspringboot.model.dto.UserDto;
import org.mingchencodelab.blogbackendspringboot.model.entity.User;
import org.mingchencodelab.blogbackendspringboot.repository.UserRepository;
import org.mingchencodelab.blogbackendspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * @param userRepository the user repository
     */
    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * @param pageable the paging and sorting information
     * @return Page<User>
     */
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        //return all users from repository with paging and sorting
        return userRepository.findAll(pageable);
    }

    /**
     * @param id the id of the user to get
     * @return Optional<User>
     */
    @Override
    public Optional<User> getUserById(Long id) {
        //get user by id from repository
        return userRepository.findById(id);
    }

    /**
     * @param user the user to create
     * @return Optional<User>
     */
    @Override
    public Optional<UserDto> createUser(UserDto user) throws Exception {
        //check if username(unique) exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("Username already exists");
        }
        //convert userDto to user
        User userToCreate = UserDto.fromUserDtoToUser(user);
        //create user from repository with user param
        User userCreated = userRepository.save(userToCreate);
        //convert user to userDto
        UserDto userDtoCreated = UserDto.fromUserToUserDto(userCreated);
        //return userDto
        return Optional.of(userDtoCreated);
    }

    /**
     * @param id   the id of the user to update
     * @param user the user to update
     * @return Optional<User>
     */
    @Override
    public Optional<UserDto> updateUser(Long id, UserDto user) throws Exception{
        //update user from repository with id and user
        return userRepository.findById(id).map(userUpdate -> {
            userUpdate.setUsername(user.getUsername());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setFullName(user.getFullName());
            return Optional.of(userRepository.save(userUpdate)).map(UserDto::fromUserToUserDto).orElse(null);
        });
    }


    /**
     * @param id the id of the user to delete
     * @return boolean if the user is deleted
     */
    @Override
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * @param username the username of the user to get
     * @apiNote get user by username
     * @return Optional<User>
     */
    @Override
    public Optional<User> getUserByUsername(String username) {
        //get user by username from repository
        //return if user is present, otherwise return null
        return userRepository.findByUsername(username);
    }
}
