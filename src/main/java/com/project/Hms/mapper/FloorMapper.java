package com.project.Hms.mapper;

import com.project.Hms.DTO.Requests.CreateFloor;
import com.project.Hms.DTO.Requests.CreateWing;
import com.project.Hms.Entity.Floor;
import com.project.Hms.Entity.Wing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface FloorMapper {

    @Mapping(source = "floorName", target = "floorName")
    Floor toFloor(CreateFloor createFloorDTO);
}
