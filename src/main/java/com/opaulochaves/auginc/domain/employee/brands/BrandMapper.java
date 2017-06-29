package com.opaulochaves.auginc.domain.employee.brands;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * This class is a mapper class that is used to transform {@link Brand}
 * objects into {@link BrandDTO} objects.
 *
 * @author Paulo Chaves
 */
public class BrandMapper {
    
    /**
     * Transforms the list of {@link Brand} objects given as a method
     * parameter into a list of {@link BrandDTO} objects and returns the
     * created list.
     *
     * @param entities
     * @return
     */
    static List<BrandDTO> mapEntitiesIntoDTOs(Iterable<Brand> entities) {
        List<BrandDTO> dtos = new ArrayList<>();
        entities.forEach(e -> dtos.add(mapEntityIntoDTO(e)));
        return dtos;
    }
    
    /**
     * Transforms the {@link Brand} object given as a method parameter into a
     * {@link BrandDTO} object and returns the created object.
     *
     * @param entity
     * @return
     */
    static BrandDTO mapEntityIntoDTO(Brand entity) {
        BrandDTO dto = new BrandDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmployeeID(entity.getEmployee().getId());
        dto.setCommission(entity.getCommission());

        return dto;
    }

    /**
     * Transforms {@code Page<ENTITY>} objects into {@code Page<DTO>} objects.
     * @param pageRequest   The information of the requested page.
     * @param source        The {@code Page<ENTITY>} object.
     * @return The created {@code Page<DTO>} object.
     */
    static Page<BrandDTO> mapEntityPageIntoDTOPage(Pageable pageRequest, Page<Brand> source) {
        List<BrandDTO> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }
}
