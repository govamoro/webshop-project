package com.radnoti.webshop.service;

import com.radnoti.webshop.mapper.MaterialMapper;
import com.radnoti.webshop.model.dto.MaterialDto;
import com.radnoti.webshop.model.entity.Material;
import com.radnoti.webshop.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private MaterialRepository materialRepository;
    private MaterialMapper materialMapper;

    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    public List<MaterialDto> getAllMaterials() {
        Iterable<Material> materialsIterable = materialRepository.findAll();
        List<Material> materials = new ArrayList<>();
        materialsIterable.forEach(materials::add);

        return materials.stream()
                .map(materialMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
}


