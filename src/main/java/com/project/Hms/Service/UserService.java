package com.project.Hms.Service;

import com.project.Hms.DTO.Response.GenericResponse;
import com.project.Hms.Entity.Room;
import com.project.Hms.Entity.User;
import com.project.Hms.Exceptions.BadRequestException;
import com.project.Hms.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomService roomService;

    //find user by id
    public User findUserById(Long id){

        return userRepository.findByUserId(id);
    }

    // find room id with users id
    public Long findRoomIdWithUserId(Long userId){

        User user = findUserById(userId);
        return user.getRoomId();
    }



    // assign a user to a room
    @Transactional
    public ResponseEntity<?> assignUserToRoom(Long userId , Long roomId){
        // check if room exists
        Room room = roomService.findById(roomId);
        if (room == null) throw new BadRequestException("room doesn't exist");

        // check if room is full
        Boolean check = roomService.isRoomFull(roomId);
        if(check) throw  new BadRequestException("Room is full");

        // find user by id
        User user = userRepository.findByUserId(userId);

        user.setRoomId(roomId);



        // Enter user into room
        user.setId(user.getId());
        user.setUserName(user.getUserName());
        user.setEmail(user.getEmail());
        User rs = userRepository.save(user);

        // Increase member count
        roomService.increaseMemberCount(roomId);


        return  ResponseEntity.ok(new GenericResponse("00",
                HttpStatus.OK,
                "User with user id " + userId +" has been added to room " + roomId,
                HttpStatus.OK));

    }

}
