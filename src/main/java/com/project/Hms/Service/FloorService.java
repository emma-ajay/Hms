package com.project.Hms.Service;

import com.project.Hms.DTO.Requests.CreateFloor;
import com.project.Hms.Entity.Floor;
import com.project.Hms.Repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class FloorService {
    @Autowired
    FloorRepository floorRepository;

    public Floor save(Floor floor){return floorRepository.save(floor);}

    public Floor findFloorByName(String floorName) {
    return floorRepository.findFloorByName(floorName);
    }

    public Floor findFloorById(Long floorId) {
        return floorRepository.findFloorById(floorId);
    }

    public Floor updateFloor(Floor floor, CreateFloor updateFloor) {
        floor.setFloorName(updateFloor.getFloorName());
        floor.setReserved(updateFloor.getReserved());
        floorRepository.save(floor);
        return floor;
    }
}
