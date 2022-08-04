package com.project.Hms.Service;

import com.project.Hms.Entity.User;
import com.project.Hms.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    @Autowired
    UserRepository userRepository;

    //find user by id
    public User findUserById(Long id){

        return userRepository.findByUserId(id);
    }

    // find room id with users id
    public Long findRoomIdWithUserId(Long userId){

        User user = findUserById(userId);
        return user.getRoomId();
    }

}
