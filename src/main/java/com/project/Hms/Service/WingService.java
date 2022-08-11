package com.project.Hms.Service;

import com.project.Hms.DTO.Requests.CreateWing;
import com.project.Hms.Entity.Wing;
import com.project.Hms.Repository.WingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class WingService {
    @Autowired
    WingRepository wingRepository;

    public void save(Wing wing){
        wingRepository.save(wing);
    }

    public Wing findWingByHall(String wingName) {
        return wingRepository.findWingByName(wingName);
    }

    public Wing getById(Long wingId){
        return wingRepository.getWingById(wingId);
    }

    public List<Wing> getWingsByHall() {
        return wingRepository.findAll();
    }

    public List<Wing> getAllWings() {
        return null;
    }

    public Wing updateWing(Wing wing, CreateWing updateWingDTO){
        wing.setWingName(updateWingDTO.getWingName());
        wing.setReserved(updateWingDTO.getReserved());
        return wing;
    }
}
