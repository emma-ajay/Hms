package com.project.Hms.Controller;

import com.project.Hms.DTO.Requests.CreateHall;
import com.project.Hms.DTO.Response.GenericResponse;
import com.project.Hms.Entity.Floor;
import com.project.Hms.Entity.Hall;
import com.project.Hms.Entity.Wing;
import com.project.Hms.Service.HallService;
import com.project.Hms.Service.Hall_Wing_Floor_Service;
import com.project.Hms.Service.WingService;
import com.project.Hms.mapper.HallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private WingService wingService;

    @Autowired
    private HallMapper hallMapper;

    @Autowired
    Hall_Wing_Floor_Service hall_wing_floor_service;


    // view all halls
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PORTER', 'ROLE_STUDENT')")
    @GetMapping
    public ResponseEntity<GenericResponse> viewHalls() {
        try {
            String message = "Request successful";
            if (hallService.getHalls() == null) {
                message = "No Halls Available";
            }

            return new ResponseEntity<>
                    (new GenericResponse("00",
                            HttpStatus.OK,
                            message,
                            hallService.getHalls()),
                            new HttpHeaders(),
                            HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GenericResponse("99",
                    HttpStatus.BAD_REQUEST,
                    e.getLocalizedMessage()),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    //view a hall by id
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PORTER', 'ROLE_STUDENT')")
    @GetMapping(path = "/view/{hallId}")
    public ResponseEntity<GenericResponse> viewHallById(@PathVariable("hallId") Long hallId) {
        try {
            String message = "Request successful";
            Hall hall = hallService.findHallById(hallId);
            if (hall == null) {
                message = "Hall does not exist";
                return new ResponseEntity<>(new GenericResponse(
                        "99", HttpStatus.BAD_REQUEST,
                        message),
                        new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>
                    (new GenericResponse("00",
                            HttpStatus.OK,
                            message,
                            hallService.findHallById(hallId)),
                            new HttpHeaders(),
                            HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GenericResponse("99",
                    HttpStatus.BAD_REQUEST,
                    e.getLocalizedMessage()),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    //view a hall by name
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PORTER', 'ROLE_STUDENT')")
    @GetMapping(path = "{hallName}/view")
    public ResponseEntity<GenericResponse> viewHallByName(@PathVariable String hallName) {
        try {
            String message = "Request successful";
            Hall hall = hallService.findHallByName(hallName);
            if (hall == null) {
                message = "Hall does not exist";
                return new ResponseEntity<>(new GenericResponse(
                        "99", HttpStatus.BAD_REQUEST,
                        message),
                        new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>
                    (new GenericResponse("00",
                            HttpStatus.OK,
                            message,
                            hallService.findHallByName(hallName)),
                            new HttpHeaders(),
                            HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GenericResponse("99",
                    HttpStatus.BAD_REQUEST,
                    e.getLocalizedMessage()),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    //view a hall by gender
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PORTER', 'ROLE_STUDENT')")
    @GetMapping(path = "{hallGender}/viewByGender")
    public ResponseEntity<GenericResponse> viewHallByGender(@PathVariable String hallGender) {
        try {
            String message = "Request successful";
            List<Hall> gender_halls = hallService.findHallsByGender(hallGender);
            if (gender_halls == null) {
                message = "No halls available"; //should there be bad request
            }
            return new ResponseEntity<>
                    (new GenericResponse("00",
                            HttpStatus.OK,
                            message,
                            hallService.findHallsByGender(hallGender)),
                            new HttpHeaders(),
                            HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GenericResponse("99",
                    HttpStatus.BAD_REQUEST,
                    e.getLocalizedMessage()),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    //create a hall
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create_hall")
    public ResponseEntity<GenericResponse> createAHall(
            @RequestBody CreateHall CreateHall) {
        try {

            Hall hall = hallService.findHallByName(CreateHall.getHallName()); //what of casing?

            if (hall != null) {

                return new ResponseEntity<>(new GenericResponse(
                        "99", HttpStatus.BAD_REQUEST,
                        "Hall Already Exists"),
                        new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);
            }
//
//            LocalDateTime localDateTime = LocalDateTime.now();
//            String dateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            hall = hallMapper.toHall(CreateHall);
            hallService.save(hall);

            return new ResponseEntity<>(new GenericResponse(
                    "00", HttpStatus.CREATED,
                    "Hall Created Successfully", hall),
                    new HttpHeaders(),
                    HttpStatus.CREATED);

        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>(new GenericResponse(
                    "99",
                    HttpStatus.BAD_REQUEST,
                    e.getLocalizedMessage()),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    //update a hall
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update_Hall/{hallId}")
    public ResponseEntity<GenericResponse> updateHall(@PathVariable("hallId") Long hallId,
                                                      @RequestBody CreateHall updateCreateHall) {// request params instead

        try {
            String message = "Hall updated successfully";
            Hall hall = hallService.findHallById(hallId);

            if (hall == null) {
                message = "Hall does not exist";
                return new ResponseEntity<>(new GenericResponse(
                        "99", HttpStatus.BAD_REQUEST,
                        message),
                        new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);
            }

            //last modified?
            return new ResponseEntity<>
                    (new GenericResponse("00",
                            HttpStatus.OK,
                            message,
                            hallService.updateHall(hall, updateCreateHall)),
                            new HttpHeaders(),
                            HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GenericResponse("99",
                    HttpStatus.BAD_REQUEST,
                    e.getLocalizedMessage()),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    // get all wings in a hall
    @GetMapping(path = "/{hallId}/wings")
    public  ResponseEntity<GenericResponse> getWingsInHall(@PathVariable Long hallId){

        try{

            String message = "Request successful";
            Hall hall = hallService.findHallById(hallId);
            if (hall == null) {
                message = "Hall does not exist";
                return new ResponseEntity<>(new GenericResponse(
                        "99", HttpStatus.BAD_REQUEST,
                        message),
                        new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);
            }
            List<Wing> wingsInHall = hall_wing_floor_service.viewAllWingsInHall(hallId);

            return new ResponseEntity<>
                    (new GenericResponse("00",
                            HttpStatus.OK,
                            message,
                            wingsInHall),
                            new HttpHeaders(),
                            HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GenericResponse("99",
                    HttpStatus.BAD_REQUEST,
                    e.getLocalizedMessage()),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }
    }

// get all floors in a wing
@GetMapping(path = "/{hallId}/{wingId}/floors")
public  ResponseEntity<GenericResponse> getAllFloorsInAWingInHall(@PathVariable Long hallId,@PathVariable Long wingId){

    try{

        String message = "Request successful";
        Hall hall = hallService.findHallById(hallId);
        Wing wing = wingService.findWingById(wingId);
        if (hall == null) {
            message = "Hall does not exist";
            return new ResponseEntity<>(new GenericResponse(
                    "99", HttpStatus.BAD_REQUEST,
                    message),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }
        if (wing == null) {
            message = "Wing does not exist";
            return new ResponseEntity<>(new GenericResponse(
                    "99", HttpStatus.BAD_REQUEST,
                    message),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }
        List<Floor> floorsInWingHAll = hall_wing_floor_service.viewAllFloorsInWingHAll(wingId,hallId);

        return new ResponseEntity<>
                (new GenericResponse("00",
                        HttpStatus.OK,
                        message,
                        floorsInWingHAll),
                        new HttpHeaders(),
                        HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new GenericResponse("99",
                HttpStatus.BAD_REQUEST,
                e.getLocalizedMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }
}


    @PostMapping(path = "/assign/{hallId}/{wingId}")
    public ResponseEntity<?> assignWingToHall(@PathVariable Long hallId, @PathVariable Long wingId){
        return  hall_wing_floor_service.assignWingToHall(hallId,wingId);
    }

    @PostMapping(path = "/assign/{hallId}/{wingId}/{floorId}")
    public ResponseEntity<?> assignFloorToWingHall(@PathVariable Long hallId, @PathVariable Long wingId,@PathVariable Long floorId){
        return  hall_wing_floor_service.assignFloorToWingHall(hallId,wingId,floorId);
    }



}
