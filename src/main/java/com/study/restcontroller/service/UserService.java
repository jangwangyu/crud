package com.study.restcontroller.service;


import com.study.restcontroller.domain.User;
import com.study.restcontroller.domain.UserDto;
import com.study.restcontroller.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public UserDto create(String name, String gender) {
        return UserDto.of(userRepository.create(name,gender));
    }

    public List<UserDto> findByGender(String gender) {
        return userRepository.findByGender(gender)
                .stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    public UserDto updateName(int id, String name) {return UserDto.of(userRepository.updateName(id,name));}

    public UserDto delete(int id) {
        return UserDto.of(userRepository.delete(id));
    }
}
