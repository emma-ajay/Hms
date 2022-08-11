package com.project.Hms.Service;

import com.project.Hms.Entity.Wing;
import com.project.Hms.Repository.WingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
