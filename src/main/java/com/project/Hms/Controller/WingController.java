package com.project.Hms.Controller;



import com.project.Hms.DTO.Requests.CreateWing;
import com.project.Hms.DTO.Response.GenericResponse;
import com.project.Hms.Entity.Wing;
import com.project.Hms.Entity.Wing;
import com.project.Hms.Service.Hall_Wing_Floor_Service;
import com.project.Hms.Service.WingService;
import com.project.Hms.mapper.WingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wings")
public class WingController {

    @Autowired
    WingService wingService;

    @Autowired
    private WingMapper wingMapper;



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update_wing/{wingId}")
    public ResponseEntity<GenericResponse> updateWing(@PathVariable("wingId") Long wingId,
                                                      @RequestBody CreateWing updateWing) {// request params instead

        try {
            String message = "Wing updated successfully";
            Wing wing = wingService.findWingById(wingId);

            if (wing == null) {
                message = "Wing does not exist";
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
                            wingService.updateWing(wing, updateWing)),
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

    @GetMapping(path = "/{hallId}/hall")
    public List<Wing> getWingsInHall(@PathVariable Long hallId){
        return hall_wing_floor_service.viewAllWingsInHall(hallId);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create_wing")
    public ResponseEntity<GenericResponse> createAWing(
            @RequestBody CreateWing createWing) {
        try {

            Wing wing = wingService.findWingByHall(createWing.getWingName()); //what of casing?

            if (wing != null) {

                return new ResponseEntity<>(new GenericResponse(
                        "99", HttpStatus.BAD_REQUEST,
                        "Wing Already Exists"),
                        new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);
            }
//
//            LocalDateTime localDateTime = LocalDateTime.now();
//            String dateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            wing = wingMapper.toWing(createWing);
            wingService.save(wing);

            return new ResponseEntity<>(new GenericResponse(
                    "00", HttpStatus.CREATED,
                    "Wing Created Successfully", wing),
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

}
