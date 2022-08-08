package com.project.Hms.Controller;


import com.project.Hms.DTO.Requests.CreateRoom;
import com.project.Hms.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
