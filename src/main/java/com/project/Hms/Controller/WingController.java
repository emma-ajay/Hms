package com.project.Hms.Controller;


import com.project.Hms.Service.Hall_Wing_Floor_Service;
import com.project.Hms.Service.WingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wings")
public class WingController {

    @Autowired
    WingService wingService;

    @Autowired
    Hall_Wing_Floor_Service hall_wing_floor_service;

    @GetMapping(path = "/{hallId}/hall")
    public List<Long> getWingsInHall(@PathVariable Long hallId){
        return hall_wing_floor_service.viewAllWingsInHall(hallId);
    }
}
