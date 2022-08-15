package com.example.project_assessment.controller;

import com.example.project_assessment.dto.CreateReadingListDTO;
import com.example.project_assessment.dto.CreateUserDTO;
import com.example.project_assessment.dto.ReadingListDTO;
import com.example.project_assessment.dto.UserResponseDTO;
import com.example.project_assessment.model.ReadingList;
import com.example.project_assessment.model.User;
import com.example.project_assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Create a user
    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return userService.create(createUserDTO);
    }

    //Delete a yser
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteById(id);
    }

    //Gets the given user’s reading lists.
    @GetMapping("/{id}/reading_lists")
    public List<ReadingListDTO> getUserReadingList(@PathVariable int id) {
        return userService.getUserReadingListByID(id);
    }

    //Create a new reading list for the user with the given ID.
    @PostMapping("/{id}/reading_lists")
    public ReadingListDTO createReadingList(@Valid @RequestBody CreateReadingListDTO createReadingListDTO, @PathVariable int id) {
        return userService.createUserReadingListByID(createReadingListDTO, id);
    }

    //Gets the given user’s reading list with the ID list_id.
    @GetMapping("/{id}/reading_lists/{list_id}")
    public ReadingListDTO getUserReadingListByListID(@PathVariable int userID, @PathVariable int listID){
        return userService.getUserReadingListByListID(userID, listID);
    }
    //Not required endpoints below:
    //Get all users
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAll();
    }

    //Get a specific user by ID
    @GetMapping("/{id}")
    public UserResponseDTO getUserByID(@PathVariable int id) {
        return userService.getById(id);
    }

}
