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



}
