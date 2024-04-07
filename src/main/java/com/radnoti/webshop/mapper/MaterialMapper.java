package com.radnoti.webshop.mapper;

import com.radnoti.webshop.model.dto.ArtDto;
import com.radnoti.webshop.model.dto.MaterialDto;
import com.radnoti.webshop.model.entity.Art;
import com.radnoti.webshop.model.entity.Material;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    MaterialDto fromEntityToDto(Material material);
    List<MaterialDto> fromEntityToDto(List<Material> materialList);
    Material fromDtoToEntity(MaterialDto materialDto);

}
