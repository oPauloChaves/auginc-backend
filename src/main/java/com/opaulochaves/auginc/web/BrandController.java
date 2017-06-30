package com.opaulochaves.auginc.web;

import com.opaulochaves.auginc.domain.employee.brands.BrandDTO;
import com.opaulochaves.auginc.domain.employee.brands.BrandService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Paulo Chaves
 */
@RestController
@Slf4j
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * Finds brands entries whose name contains the given search term. This
     * search is case insensitive.
     *
     * @param searchTerm The used search term.
     * @param pageRequest The information of the requested page.
     * @return
     */
    @RequestMapping(value = "/api/brands/search", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<BrandDTO> findBySearchTerm(@RequestParam("q") String searchTerm, Pageable pageRequest) {
        LOG.debug("Finding brand entries by search term: {} and page request: {}", searchTerm, pageRequest);

        Page<BrandDTO> searchResult = brandService.findBySearchTerm(searchTerm, pageRequest);
        LOG.debug("Found {} todo entries. Returned page {} contains {} todo entries",
                searchResult.getTotalElements(),
                searchResult.getNumber(),
                searchResult.getNumberOfElements()
        );

        return searchResult;
    }

    /**
     * Find brands entries of an employee whose brand name contains the search
     * term. The search is case insensitive
     *
     * @param employeeID The employee ID
     * @param searchTerm The used search term
     * @param pageRequest The information of the page requested
     * @return
     */
    @RequestMapping(value = "/api/employees/{employeeID}/brands/search", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN') or @webSecurity.checkUserID(authentication,#employeeID)")
    public Page<BrandDTO> findEmployeesBrandsBySearchTerm(@PathVariable("employeeID") Long employeeID,
            @RequestParam("q") String searchTerm, Pageable pageRequest) {
        LOG.debug("Finding employees brand entries by search term: {} and page request: {}", searchTerm, pageRequest);

        Page<BrandDTO> searchResult = brandService.findEmployeesBrandsBySearchTerm(searchTerm, employeeID, pageRequest);
        LOG.debug("Found {} todo entries. Returned page {} contains {} todo entries",
                searchResult.getTotalElements(),
                searchResult.getNumber(),
                searchResult.getNumberOfElements()
        );

        return searchResult;
    }

    /**
     * Create a new brand entry.
     *
     * @param employeeID The employee ID
     * @param newEntry The information of the created brand entry.
     * @return The information of the created brand entry.
     */
    @RequestMapping(value = "/api/employees/{employeeID}/brands", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or @webSecurity.checkUserID(authentication,#employeeID)")
    public BrandDTO create(@PathVariable("employeeID") Long employeeID, @RequestBody @Valid BrandDTO newEntry) {
        newEntry.setEmployeeID(employeeID);
        LOG.info("Creating a new brand entry by using information: {}", newEntry);

        BrandDTO created = brandService.create(newEntry);
        LOG.info("Created a new brand entry: {}", created);

        return created;
    }
    
    /**
     * Update an brand entry.
     *
     * @param employeeID The employee ID
     * @param updatedEntry The information of the existing brand entry.
     * @return The information of the updated brand entry.
     */
    @RequestMapping(value = "/api/employees/{employeeID}/brands", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN') or @webSecurity.checkUserID(authentication,#employeeID)")
    public BrandDTO update(@PathVariable("employeeID") Long employeeID, @RequestBody @Valid BrandDTO updatedEntry) {
        updatedEntry.setEmployeeID(employeeID);
        LOG.info("Updating brand entry by using information: {}", updatedEntry);

        BrandDTO updated = brandService.update(updatedEntry);
        LOG.info("Updated a brand entry: {}", updated);

        return updated;
    }

    /**
     * Finds all brand entries.
     *
     * @param pageRequest
     * @return The information of all brand entries.
     */
    @RequestMapping(value = "/api/brands", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<BrandDTO> findAll(Pageable pageRequest) {
        LOG.info("Finding all brand entries");

        Page<BrandDTO> pageResult = brandService.findAll(pageRequest);
        LOG.debug("Found {} todo entries. Returned page {} contains {} todo entries",
                pageResult.getTotalElements(),
                pageResult.getNumber(),
                pageResult.getNumberOfElements()
        );

        return pageResult;
    }

    /**
     * Finds all brand entries of an employee.
     *
     * @param employeeID The employee ID
     * @return The information of all brand entries of an employee
     */
    @RequestMapping(value = "/api/employees/{employeeID}/brands", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN') or @webSecurity.checkUserID(authentication,#employeeID)")
    public List<BrandDTO> findByEmployeeID(@PathVariable Long employeeID) {
        LOG.info("Finding all brand entries of an employee");

        List<BrandDTO> entries = brandService.findByEmployeeID(employeeID);
        LOG.info("Found {} brand entries.", entries.size());

        return entries;
    }

    /**
     * Finds a single brand entry
     *
     * @param id
     * @return The information of a single brand entry
     */
    @RequestMapping(value = "/api/brands/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public BrandDTO findById(@PathVariable Long id) {
        LOG.info("Finding brand by using id: {}", id);

        BrandDTO entry = brandService.findByID(id);
        LOG.info("Found brand entry: {}", entry);

        return entry;
    }

    /**
     * Finds a single brand of an employee.
     *
     * @param id The brand ID
     * @param employeeID The employee ID
     * @return The information of a single brand of an employee
     */
    @RequestMapping(value = "/api/employees/{employeeID}/brands/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN') or @webSecurity.checkUserID(authentication,#employeeID)")
    public BrandDTO findBrandsEmployeeByID(@PathVariable Long id, @PathVariable Long employeeID) {
        LOG.info("Finding brand by using id: {}", id);

        BrandDTO entry = brandService.findBrandsEmployeeByID(id, employeeID);
        LOG.info("Found brand entry: {}", entry);

        return entry;
    }
}
