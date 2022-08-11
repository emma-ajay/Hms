package com.project.Hms.Service;

import com.project.Hms.DTO.Requests.CreateRoom;
import com.project.Hms.DTO.Response.GenericResponse;
import com.project.Hms.Entity.Floor;
import com.project.Hms.Entity.Hall;
import com.project.Hms.Entity.Room;
import com.project.Hms.Entity.Wing;
import com.project.Hms.Exceptions.BadRequestException;
import com.project.Hms.Repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HallService hallService;

    @Autowired
    FloorService floorService;

    @Autowired
    WingService wingService;

    @Autowired
    Hall_Wing_Floor_Service hall_wing_floor_service;

    @Autowired
    ModelMapper modelMapper;


        // check if room Exists
        Boolean checkRoomExists(String roomNumber){
            return roomRepository.existsByRoomNumber(roomNumber);
        }

        // create a room
        public ResponseEntity<?> createRoom(CreateRoom room){
        String roomNumber = room.getRoomNumber();
        Long  hallId = room.getHallId();
        Long floorId= room.getFloorId();
        Long wingId = room.getWingId();


        // check if room already exists
            Boolean roomCheck = checkRoomExists(roomNumber);
            if (roomCheck) throw  new BadRequestException("Room already exists");

        // check that hall exist
            Hall hall = hallService.findHallById(hallId);
             if (hall == null) throw  new BadRequestException("hall doesn't exist");

        // check that wing exists in hall
            Boolean check = hall_wing_floor_service.checkIfWingIsAssignedToHall(hallId,wingId);
            if (!check) throw  new BadRequestException("Wing doesn't exist in hall");

        // check that floor exist in wing in hall
            Boolean check2 = hall_wing_floor_service.checkIfFloorExistInWingHall(hallId,wingId,floorId);
            if (!check2) throw  new BadRequestException("floor doesn't exist in hall");


        //    Room roomCreated = modelMapper.map(room, Room.class);
            Room roomCreated = new Room();
            roomCreated.setRoomNumber(roomNumber);
            roomCreated.setHallId(hallId);
            roomCreated.setWingId(wingId);
            roomCreated.setFloorId(floorId);
            roomCreated.setFull(Boolean.FALSE);
            roomCreated.setReserved(Boolean.FALSE);
            roomCreated.setMemberCount(0L);
            Room rs = roomRepository.save(roomCreated);

        return  ResponseEntity.ok(new GenericResponse("00",
                HttpStatus.OK,
                "Room number "+ roomNumber + "created  in hall " + hallId + " wing " + wingId + " floor  " + floorId  ,
                room,
                HttpStatus.CREATED));
    }


    // view room by room id

    public Room findById(Long roomId){
        return roomRepository.findRoomByRoomId(roomId);
    }


    public ResponseEntity<?> findRoomById(Long roomId){
        Optional<Room> room = roomRepository.findById(roomId);
        return  ResponseEntity.ok(new GenericResponse("00",
                HttpStatus.OK,
                "View room with id " + roomId,
                room,
                HttpStatus.OK));
    }

    // view room by room number
    public  Room findRoomByRoomNumber(String roomNumber){
            Room room = roomRepository.findRoomByRoomNumber(roomNumber);
            return  room;
    }
    // view all rooms
    public  ResponseEntity<?> viewAllRooms(){
        List<Room> roomList = roomRepository.findAll();
        return ResponseEntity.ok( new GenericResponse("00",
                HttpStatus.OK,
                "View all rooms",
                roomList,
                HttpStatus.OK));

    }

    // view all rooms in a hall
    public ResponseEntity<?> viewAllRoomsByHall(String hallName){
        Hall hall = hallService.findHallByName(hallName);
        if (hall == null) throw new BadRequestException("hall doesn't exist");
        Long hallId = hall.getHallId();
        List<Room> roomList = roomRepository.findRoomByHallId(hallId);
        return ResponseEntity.ok( new GenericResponse("00",
                HttpStatus.OK,
                "View all rooms in hall id" + hallId,
                roomList,
                HttpStatus.OK));

    }

    //view all rooms in a wing in a hall

    // view all rooms in a floor in a wing in a hall

    // view all rooms that are not full in a hall

    // view all rooms reserved in a hall

    //check if room is full
    public Boolean isRoomFull(Long roomId){
        Room room = findById(roomId);

        Long members = room.getMemberCount();

        Long hallId = room.getHallId();

        Hall hall = hallService.findHallById(hallId);

        Long hallCapacity = hall.getHallCapacity();

        if (members == hallCapacity){
            return true;
        }

        else {
            return false;
        }

    }


    // increase member count by one when a user is added to room

    public void  increaseMemberCount(Long roomId){
            Room room = findById(roomId);
            Long currentCount = room.getMemberCount();
            Long newCount = currentCount + 1;
            room.setRoomId(room.getRoomId());
            room.setMemberCount(currentCount);
          Room rs = roomRepository.save(room);
        //    roomRepository.updateMemberCount(newCount,roomId);
    }

}
