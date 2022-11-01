package com.project.Hms.Controller;


import com.project.Hms.DTO.Requests.CreateRoom;
import com.project.Hms.DTO.Response.GenericResponse;
import com.project.Hms.Entity.Room;
import com.project.Hms.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping("/new")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoom room){
        return roomService.createRoom(room);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findRoomById(@PathVariable Long id){
        return roomService.findRoomById(id);
    }

    @GetMapping(path = "/all")
    public  ResponseEntity<?> viewAllRooms(){
        return roomService.viewAllRooms();
    }

    @GetMapping(path = "hall/{hallName}")
    public ResponseEntity<?> viewAllRoomsByHall( @PathVariable String hallName){
        return roomService.viewAllRoomsByHall(hallName);
    }

    @GetMapping("/{hallId}/{wingId}/{floorId}")
    public ResponseEntity<?> viewRoomsByHallWingAndFloorId(@PathVariable Long hallId, @PathVariable Long wingId,
                                                           @PathVariable Long floorId ){
        List<Room> roomList=  roomService.viewRoomsByHallWingAndFloorId(hallId, wingId, floorId);
        return new ResponseEntity<>
                (new GenericResponse("00",
                        HttpStatus.OK,
                        "rooms in hall wing floor ",
                        roomList),
                        new HttpHeaders(),
                        HttpStatus.OK);

}


}
