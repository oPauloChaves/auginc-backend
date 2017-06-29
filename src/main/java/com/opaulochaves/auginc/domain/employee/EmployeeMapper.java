package com.opaulochaves.auginc.domain.employee;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * This class is a mapper class that is used to transform {@link Employee}
 * objects into {@link EmployeeDTO} objects.
 *
 * @author Paulo Chaves
 */
public class EmployeeMapper {

    /**
     * Transforms the list of {@link Employee} objects given as a method
     * parameter into a list of {@link EmployeeDTO} objects and returns the
     * created list.
     *
     * @param entities
     * @return
     */
    static List<EmployeeDTO> mapEntitiesIntoDTOs(Iterable<Employee> entities) {
        List<EmployeeDTO> dtos = new ArrayList<>();
        entities.forEach(e -> dtos.add(mapEntityIntoDTO(e)));
        return dtos;
    }

    /**
     * Transforms the {@link Employee} object given as a method parameter into a
     * {@link EmployeeDTO} object and returns the created object.
     *
     * @param entity
     * @return
     */
    static EmployeeDTO mapEntityIntoDTO(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setTitle(entity.getTitle());
        dto.setEnabled(entity.isEnabled());

        return dto;
    }

    /**
     * Transforms {@code Page<ENTITY>} objects into {@code Page<DTO>} objects.
     * @param pageRequest   The information of the requested page.
     * @param source        The {@code Page<ENTITY>} object.
     * @return The created {@code Page<DTO>} object.
     */
    static Page<EmployeeDTO> mapEntityPageIntoDTOPage(Pageable pageRequest, Page<Employee> source) {
        List<EmployeeDTO> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }
}
