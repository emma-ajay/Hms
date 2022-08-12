package com.project.Hms.Service;


import com.project.Hms.DTO.Response.GenericResponse;
import com.project.Hms.Entity.Hall;
import com.project.Hms.Entity.Hall_Wing_Floor;
import com.project.Hms.Entity.Wing;
import com.project.Hms.Exceptions.BadRequestException;
import com.project.Hms.Repository.Hall_Wing_FloorRepository;
import com.sun.xml.bind.v2.model.core.WildcardMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Hall_Wing_Floor_Service {

    @Autowired
    Hall_Wing_FloorRepository hall_wing_floorRepository;

    @Autowired
    HallService hallService;

    @Autowired
    WingService wingService;

    // assign wing to a hall

    public ResponseEntity<?> assignWingToHall(Long hallId , Long wingId){

        // check if hall exists
        Hall hall = hallService.findHallById(hallId);
        if (hall == null) throw  new BadRequestException("hall doesn't exist");

        // check if wing exist
        Wing wing = wingService.findWingById(wingId);
        if (wing == null) throw  new BadRequestException("Wing doesn't exist");

        Hall_Wing_Floor hall_wing_floor = new Hall_Wing_Floor(hallId,wingId);
        Hall_Wing_Floor rs = hall_wing_floorRepository.save(hall_wing_floor);
        return ResponseEntity.ok(new GenericResponse("00",
                HttpStatus.OK,
                "wing  id " + wingId + " has assigned to hall id " + hallId ,
                HttpStatus.OK));
    }

    // assign floor to wing hall
    public ResponseEntity<?> assignFloorToWingHall(Long hallId , Long wingId, Long floorId){

        // check if wing is assigned to hall
        Boolean check = checkIfWingIsAssignedToHall(hallId,wingId);
        if(!check) throw  new BadRequestException("Wing isn't assigned to Hall");

        // assign floor
        Hall_Wing_Floor hall_wing_floor = new Hall_Wing_Floor(hallId,wingId,floorId);
        Hall_Wing_Floor rs = hall_wing_floorRepository.save(hall_wing_floor);
        return ResponseEntity.ok(new GenericResponse("00",
                HttpStatus.OK,
                "floor with Id "+ floorId +" has been assigned to wing  id " + wingId + " in hall id " + hallId ,
                HttpStatus.OK));


    }

    // check if Wing is assigned to hall
    public  Boolean checkIfWingIsAssignedToHall(Long hallId, Long wingId){

        List<Hall_Wing_Floor> hall_wing_floor = hall_wing_floorRepository.getAssignedHallWingById(hallId,wingId);
        int size = hall_wing_floor.size();
        if(size== 0){
            return  false;
        }
        else {
            return  true;
        }

    }

    // check if floor exists in wing in hall
    public  Boolean checkIfFloorExistInWingHall(Long hallId, Long wingId, Long floorId){
        List<Hall_Wing_Floor> hall_wing_floor = hall_wing_floorRepository.getAssignedHallWingFloorById(hallId,wingId,floorId);
        int size = hall_wing_floor.size();
        if(size== 0){
            return  false;
        }
        else {
            return  true;
        }
    }

    // view all wings in a hall
    public List<Wing> viewAllWingsInHall(Long hallId) {
        List<Long> wingListId = hall_wing_floorRepository.viewAllWingsInHall(hallId);
        List<Wing> wingList = new ArrayList<>();
        int size = wingListId.size();
        for(int i  =0; i<size; i++){
            Long wingId = wingListId.get(i);
            Wing wing = wingService.findWingById(wingId);
            wingList.add(i,wing);
        }


        return wingList;
    }

    // view all floors in a wing belonging to hall
    public List<Long> viewAllFloorsInWingHAll(Long wingId, Long hallId){
        return hall_wing_floorRepository.viewAllFloorsInWingHAll(wingId,hallId);
    }
}
