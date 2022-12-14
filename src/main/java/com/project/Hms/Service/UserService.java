package com.project.Hms.Service;

import com.project.Hms.DTO.Response.GenericResponse;
import com.project.Hms.Entity.Hall;
import com.project.Hms.Entity.Room;
import com.project.Hms.Entity.User;
import com.project.Hms.Exceptions.BadRequestException;
import com.project.Hms.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service

public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    HallService hallService;

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

        Room roomServiceById = roomService.findById(roomId);
        Long hallId =roomServiceById.getHallId();

        // find hall by id
        Hall hall = hallService.findHallById(hallId);

        // find user by id
        User user = userRepository.findByUserId(userId);


        // check if user is already in this particular room
        Long roomNum = user.getRoomId();
        if (Objects.equals(roomNum, roomId)) throw new BadRequestException("You are already in this room");

        // check if user was in a previous room and is moving
        // previous room count to be decreased and must be set to not full
        if(Objects.nonNull(roomNum)){
            roomService.decreaseMemberCount(roomNum);

                Room room1 = roomService.findById(roomNum);
                room1.setRoomId(roomNum);
                room1.setFull(Boolean.FALSE);
            roomService.roomRepository.save(room1);


        }

        // check users gender
        String gender = user.getGender();
        String hallGender = hall.getHallGender();
        if(!gender.equals(hallGender)) throw new BadRequestException("This hall isn't meant for your gender");

        // check if room is full
        Boolean check = roomService.isRoomFull(roomId);
        if(check) throw  new BadRequestException("Room is full");

        // Enter user into room
        user.setRoomId(roomId);
        user.setId(user.getId());
        user.setUserName(user.getUserName());
        user.setEmail(user.getEmail());
        User rs = userRepository.save(user);

        // Increase member count
        Room room1 = roomService.increaseMemberCount(roomId);

        // check if room is full again
        Boolean check2 = roomService.isRoomFull(roomId);
        if(check2){
            room.setRoomId(room.getRoomId());
            room.setFull(Boolean.TRUE);
            roomService.roomRepository.save(room);
        }



        return  ResponseEntity.ok(new GenericResponse("00",
                HttpStatus.OK,
                "User with user id " + userId +" has been added to room " + roomId,
                HttpStatus.OK));

    }

}
