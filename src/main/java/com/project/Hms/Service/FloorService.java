package com.project.Hms.Service;

import com.project.Hms.Entity.Floor;
import com.project.Hms.Repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class FloorService {
    @Autowired
    FloorRepository floorRepository;

    public Floor findFloorByName(String floorName) {
    return floorRepository.findFloorByName(floorName);
    }
}
