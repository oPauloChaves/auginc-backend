package com.opaulochaves.auginc.domain.employee.brands;

import com.opaulochaves.auginc.domain.common.EntryNotFoundException;
import com.opaulochaves.auginc.domain.employee.Employee;
import com.opaulochaves.auginc.domain.employee.EmployeeService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Paulo Chaves
 */
@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private EmployeeService employeeService;

    @Transactional
    @Override
    public BrandDTO create(BrandDTO newEntry) {
        LOG.info("Creating a new brand entry from: {}", newEntry);

        Employee employee = employeeService.findEntityById(newEntry.getEmployeeID());
        Brand created = new Brand();
        created.setName(newEntry.getName());
        created.setEmployee(employee);
        created.setCommission(newEntry.getCommission());

        created = brandRepository.save(created);

        return BrandMapper.mapEntityIntoDTO(created);
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long id, Long employeeID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteEmployeesBrands(Long employeeID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional(readOnly = true)
    @Override
    public List<BrandDTO> findAll() {
        LOG.info("Finding all brand entries.");

        List<Brand> allEntries = brandRepository.findAll();
        LOG.info("Found {} brand entries", allEntries.size());

        return BrandMapper.mapEntitiesIntoDTOs(allEntries);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Page<BrandDTO> findAll(Pageable pageRequest) {
        LOG.info("Finding all brand entries.");

        Page<Brand> pageResult = brandRepository.findAll(pageRequest);
        LOG.info("Found {} brand entries. Returned page {} contains {} brand entries",
                pageResult.getTotalElements(),
                pageResult.getNumber(),
                pageResult.getNumberOfElements()
        );

        return BrandMapper.mapEntityPageIntoDTOPage(pageRequest, pageResult);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BrandDTO> findByEmployeeID(Long employeeID) {
        LOG.info("Finding all brand entries of an employee ID.");

        List<Brand> allEntries = brandRepository.findByEmployee_id(employeeID);
        LOG.info("Found {} brand entries for the employee #{}", allEntries.size(), employeeID);

        return BrandMapper.mapEntitiesIntoDTOs(allEntries);
    }

    @Override
    public List<BrandDTO> findByEmployeeEmail(String employeeEmail) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional(readOnly = true)
    @Override
    public BrandDTO findByID(Long id) {
        LOG.info("Finding an brand entry with id: {}", id);

        Brand entry = findEntryById(id, null);
        LOG.info("Found brand entry: {}", entry);

        return BrandMapper.mapEntityIntoDTO(entry);
    }

    @Transactional(readOnly = true)
    @Override
    public BrandDTO findBrandsEmployeeByID(Long id, Long employeeID) {
        LOG.info("Finding an brand entry with id {} of an employee with id {}", id, employeeID);

        Brand entry = findEntryById(id, employeeID);
        LOG.info("Found an brand {}", entry);

        return BrandMapper.mapEntityIntoDTO(entry);
    }

    @Transactional
    @Override
    public BrandDTO update(BrandDTO updatedEntry) {
        LOG.info("Updating a brand entry by using information: {}", updatedEntry);

        Brand updated = findEntryById(updatedEntry.getId(), updatedEntry.getEmployeeID());
        updated.setName(updatedEntry.getName());
        updated.setCommission(updatedEntry.getCommission());

        brandRepository.flush();

        LOG.info("Updated the brand entry: {}", updated);

        return BrandMapper.mapEntityIntoDTO(updated);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BrandDTO> findBySearchTerm(String searchTerm, Pageable pageRequest) {
        LOG.info("Finding brand entries by search term: {} and page request: {}", searchTerm, pageRequest);

        Page<Brand> searchResult = brandRepository.findBySearchTerm(searchTerm, pageRequest);

        LOG.info("Found {} brand entries. Returned page {} contains {} brand entries",
                searchResult.getTotalElements(),
                searchResult.getNumber(),
                searchResult.getNumberOfElements()
        );

        return BrandMapper.mapEntityPageIntoDTOPage(pageRequest, searchResult);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BrandDTO> findEmployeesBrandsBySearchTerm(String searchTerm, Long employeeID, Pageable pageRequest) {
        LOG.info("Finding brand entries by search term: {} and page request: {} and employee: {}", searchTerm, pageRequest, employeeID);

        Page<Brand> searchResult = brandRepository.findEmployeesBrandsBySearchTerm(searchTerm, employeeID, pageRequest);

        LOG.info("Found {} brand entries. Returned page {} contains {} brand entries",
                searchResult.getTotalElements(),
                searchResult.getNumber(),
                searchResult.getNumberOfElements()
        );

        return BrandMapper.mapEntityPageIntoDTOPage(pageRequest, searchResult);
    }

    private Brand findEntryById(Long id, Long employeeID) {
        Optional<Brand> entry;

        if (employeeID != null) {
            entry = brandRepository.findByIdAndEmployee_id(id, employeeID);
        } else {
            entry = brandRepository.findById(id);
        }

        return entry.orElseThrow(() -> new EntryNotFoundException(id));
    }
}