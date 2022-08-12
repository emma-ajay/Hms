package com.project.Hms.mapper;

import com.project.Hms.DTO.HallDTO;
import com.project.Hms.Entity.Hall;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface HallMapper {

    @Mapping(source = "hallName", target = "hallName")
    Hall toHall(HallDTO createHallDTO);
}
