package com.project.Hms.Controller;

import com.project.Hms.Service.Hall_Wing_Floor_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class Hall_Wing_Floor_Controller {
    @Autowired
    Hall_Wing_Floor_Service hall_wing_floor_service;

    @PostMapping(path = "/assign/{hallId}/{wingId}")
    public ResponseEntity<?> assignWingToHall(@PathVariable Long hallId, @PathVariable Long wingId){
    return  hall_wing_floor_service.assignWingToHall(hallId,wingId);
    }

    @PostMapping(path = "/assign/{hallId}/{wingId}/{floorId}")
    public ResponseEntity<?> assignFloorToWingHall(@PathVariable Long hallId, @PathVariable Long wingId,@PathVariable Long floorId){
        return  hall_wing_floor_service.assignFloorToWingHall(hallId,wingId,floorId);
    }

}
