package com.opaulochaves.auginc.domain.employee.customers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * This class is a mapper class that is used to transform {@link Customer}
 * objects into {@link CustomerDTO} objects.
 *
 * @author Paulo Chaves
 */
public class CustomerMapper {

    /**
     * Transforms the list of {@link Customer} objects given as a method
     * parameter into a list of {@link CustomerDTO} objects and returns the
     * created list.
     *
     * @param entities
     * @return
     */
    static List<CustomerDTO> mapEntitiesIntoDTOs(Iterable<Customer> entities) {
        List<CustomerDTO> dtos = new ArrayList<>();
        entities.forEach(e -> dtos.add(mapEntityIntoDTO(e)));
        return dtos;
    }

    /**
     * Transforms the {@link Customer} object given as a method parameter into a
     * {@link CustomerDTO} object and returns the created object.
     *
     * @param entity
     * @return
     */
    static CustomerDTO mapEntityIntoDTO(Customer entity) {
        CustomerDTO dto = new CustomerDTO();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCellNumber(entity.getCellNumber());
        dto.setNote(entity.getNote());
        dto.setEmployeeID(entity.getEmployee().getId());
        dto.setAddress(entity.getAddress());

        return dto;
    }

    /**
     * Transforms {@code Page<ENTITY>} objects into {@code Page<DTO>} objects.
     *
     * @param pageRequest The information of the requested page.
     * @param source The {@code Page<ENTITY>} object.
     * @return The created {@code Page<DTO>} object.
     */
    static Page<CustomerDTO> mapEntityPageIntoDTOPage(Pageable pageRequest, Page<Customer> source) {
        List<CustomerDTO> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }
}
