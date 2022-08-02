package com.project.Hms.mapper;

import com.project.Hms.DTO.WingDTO;
import com.project.Hms.Entity.Wing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface WingMapper {

    @Mapping(source = "wingName", target = "wingName")
    Wing toWing(WingDTO createWingDTO);
}
