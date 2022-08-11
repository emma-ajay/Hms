package com.project.Hms.Service;

import com.project.Hms.DTO.Requests.CreateHall;
import com.project.Hms.Entity.Hall;
import com.project.Hms.Repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    @Autowired
    HallRepository hallRepository;

    public void save(Hall hall) {
        hallRepository.save(hall);
    }

    public Hall findHallById (Long hallId){
        return hallRepository.findHallById(hallId);

    }
    public List<Hall> getHalls() {
        return hallRepository.findAll();
    }

    public Hall findHallByName(String hallName){
        return hallRepository.findHallByName(hallName);
    }

    public List<Hall> findHallsByGender(String hallGender){
        return hallRepository.findHallsByGender(hallGender);
    }

    public Hall updateHall(Hall hall, CreateHall updateCreateHall){
        hall.setHallName(updateCreateHall.getHallName());
        hall.setHallGender(updateCreateHall.getHallGender());
        hall.setHallCapacity(updateCreateHall.getHallCapacity());
        hall.setReserved(updateCreateHall.getReserved());
        return hall;

    }







}
