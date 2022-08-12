package com.project.Hms.Controller;


import com.project.Hms.DTO.Requests.CreateFloor;
import com.project.Hms.DTO.Response.GenericResponse;
import com.project.Hms.Entity.Floor;
import com.project.Hms.Service.FloorService;
import com.project.Hms.mapper.FloorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/floor")
public class FloorController {

    @Autowired
    private FloorMapper floorMapper;

    @Autowired
    private FloorService floorService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create_floor")
    public ResponseEntity<GenericResponse> createAFloor(
            @RequestBody CreateFloor createFloor) {
        try {

            Floor floor = floorService.findFloorByName(createFloor.getFloorName()); //what of casing?

            if (floor != null) {

                return new ResponseEntity<>(new GenericResponse(
                        "99", HttpStatus.BAD_REQUEST,
                        "Floor Already Exists"),
                        new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);
            }
//
//            LocalDateTime localDateTime = LocalDateTime.now();
//            String dateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            floor = floorMapper.toFloor(createFloor);
            floorService.save(floor);

            return new ResponseEntity<>(new GenericResponse(
                    "00", HttpStatus.CREATED,
                    "Hall Created Successfully", floor),
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update_floor/{floorId}")
    public ResponseEntity<GenericResponse> updateWing(@PathVariable("floorId") Long floorId,
                                                      @RequestBody CreateFloor updateFloor) {// request params instead

        try {
            String message = "Wing updated successfully";
            Floor floor = floorService.findFloorById(floorId);

            if (floor == null) {
                message = "Floor does not exist";
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
                            floorService.updateFloor(floor, updateFloor)),
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
}
