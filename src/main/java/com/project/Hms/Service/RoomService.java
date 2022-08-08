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
    ModelMapper modelMapper;


        // check if room Exists
        Boolean checkRoomExists(String roomNumber){
            return roomRepository.existsByRoomNumber(roomNumber);
        }

        // create a room
        public ResponseEntity<?> createRoom(CreateRoom room){
        String roomNumber = room.getRoomNumber();
        String  hallName = room.getHallName();
        String floorName= room.getFloorName();
        String wingName = room.getWingName();


        // check if room already exists
            Room roomCheck = roomRepository.findRoomByRoomNumber(roomNumber);
            if (roomCheck != null) throw  new BadRequestException("Room already exists");

        // check that hall exist
            Hall hall = hallService.findHallByName(hallName);
             if (hall == null) throw  new BadRequestException("hall doesn't exist");
             Long hallId = hall.getHallId();

        // check that wing exist
            Wing wing = wingService.findWingByHall(wingName);
            if (wing == null) throw  new BadRequestException("Wing doesn't exist in hall");
            Long wingId = wing.getWingId();

        // check that floor exist in wing
            Floor floor = floorService.findFloorByName(floorName);
            if (floor == null) throw  new BadRequestException("floor doesn't exist in hall");
            Long floorId = floor.getFloorId();

            Room roomCreated = modelMapper.map(room, Room.class);
            roomCreated.setFull(Boolean.FALSE);
            roomCreated.setReserved(Boolean.FALSE);
            roomCreated.setMemberCount(0L);
            roomCreated.setRoomNumber(roomNumber);
            roomCreated.setFloorId(floorId);
            roomCreated.setHallId(hallId);
            roomCreated.setWingId(wingId);
            Room rs = roomRepository.save(roomCreated);


        return  ResponseEntity.ok(new GenericResponse("00",
                HttpStatus.OK,
                "Room number "+ roomNumber + "created  in hall " + hallName + " wing " + wingName + "floor " + floorName  ,
                room,
                HttpStatus.OK));
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

    //view all rooms in a wing

    // view all rooms in a floor

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
            roomRepository.updateMemberCount(newCount,roomId);
    }

}
