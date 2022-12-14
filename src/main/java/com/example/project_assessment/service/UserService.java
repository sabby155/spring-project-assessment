package com.example.project_assessment.service;

import com.example.project_assessment.dto.CreateReadingListDTO;
import com.example.project_assessment.dto.CreateUserDTO;
import com.example.project_assessment.dto.ReadingListDTO;
import com.example.project_assessment.dto.UserResponseDTO;
import com.example.project_assessment.exception.NotFoundException;
import com.example.project_assessment.model.ReadingList;
import com.example.project_assessment.model.User;
import com.example.project_assessment.repository.ReadingListRepository;
import com.example.project_assessment.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    private ModelMapper modelMapper;



    //POST CREATE JSON - For new user
//    {
//        "username": "user1",
//        "password": "123"
//    }
    public UserResponseDTO create(CreateUserDTO createUserDTO) {
        User user = modelMapper.map(createUserDTO, User.class);
        return modelMapper.map(repository.save(user), UserResponseDTO.class);
    }

    //POST CREATE JSON - For new user reading list by ID
//    {
//        "name": "New reading list"
//    }
    public ReadingListDTO createUserReadingListByID(CreateReadingListDTO createReadingListDTO, int id) {

        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        ReadingList readingList = modelMapper.map(createReadingListDTO, ReadingList.class);
        readingList.setName(createReadingListDTO.getName());
        readingList.setUser(user);
        readingListRepository.save(readingList);
        user.getReadingLists().add(readingList);

        ReadingListDTO readingListDTO =  modelMapper.map(readingList, ReadingListDTO.class);
        readingListDTO.setName(readingList.getName());
        return readingListDTO;
    }

    public List<UserResponseDTO> getAll() {
        return repository.findAll().stream().map(user -> modelMapper.map(user, UserResponseDTO.class)).toList();
    }


    public List<ReadingListDTO> getUserReadingListByID(int id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));

        return readingListRepository.findByUser(user).stream()
                .map(readingList -> modelMapper.map(readingList, ReadingListDTO.class))
                .toList();
    }

    public void deleteById(int id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else {
            throw new NotFoundException("User not found");
        }
    }

    public UserResponseDTO getById(int id) {
        UserResponseDTO userResponseDTO = repository
                .findById(id)
                .map(user -> modelMapper.map(user,UserResponseDTO.class)).orElseThrow(() -> new NotFoundException("User not found"));
        return userResponseDTO;
    }

    // Come back to this
    public ReadingListDTO getUserReadingListByListID(int userID, int listID) {
        User user = repository.findById(userID).orElseThrow(() -> new NotFoundException("User Not Found"));
        List<ReadingList> readingLists = readingListRepository.findByUser(user).stream()
                .filter(list -> list.getId() == listID).toList();
                ReadingList readingList = readingLists.get(0);
                return modelMapper.map(readingList, ReadingListDTO.class);
    }


}
