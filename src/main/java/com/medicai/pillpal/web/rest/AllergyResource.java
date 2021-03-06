package com.medicai.pillpal.web.rest;

import com.medicai.pillpal.service.AllergyService;
import com.medicai.pillpal.service.dto.AllergyDTO;
import com.medicai.pillpal.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.medicai.pillpal.domain.Allergy}.
 */
@RestController
@RequestMapping("/api")
public class AllergyResource {

    private final Logger log = LoggerFactory.getLogger(AllergyResource.class);

    private static final String ENTITY_NAME = "allergy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AllergyService allergyService;

    public AllergyResource(AllergyService allergyService) {
        this.allergyService = allergyService;
    }

    /**
     * {@code POST  /allergies} : Create a new allergy.
     *
     * @param allergyDTO the allergyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new allergyDTO, or with status {@code 400 (Bad Request)} if the allergy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/allergies")
    public ResponseEntity<AllergyDTO> createAllergy(@RequestBody AllergyDTO allergyDTO) throws URISyntaxException {
        log.debug("REST request to save Allergy : {}", allergyDTO);
        if (allergyDTO.getId() != null) {
            throw new BadRequestAlertException("A new allergy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AllergyDTO result = allergyService.save(allergyDTO);
        return ResponseEntity.created(new URI("/api/allergies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /allergies} : Updates an existing allergy.
     *
     * @param allergyDTO the allergyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated allergyDTO,
     * or with status {@code 400 (Bad Request)} if the allergyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the allergyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/allergies")
    public ResponseEntity<AllergyDTO> updateAllergy(@RequestBody AllergyDTO allergyDTO) throws URISyntaxException {
        log.debug("REST request to update Allergy : {}", allergyDTO);
        if (allergyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AllergyDTO result = allergyService.save(allergyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, allergyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /allergies} : get all the allergies.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of allergies in body.
     */
    @GetMapping("/allergies")
    public ResponseEntity<List<AllergyDTO>> getAllAllergies(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Allergies");
        Page<AllergyDTO> page = allergyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /allergies/:id} : get the "id" allergy.
     *
     * @param id the id of the allergyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the allergyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/allergies/{id}")
    public ResponseEntity<AllergyDTO> getAllergy(@PathVariable Long id) {
        log.debug("REST request to get Allergy : {}", id);
        Optional<AllergyDTO> allergyDTO = allergyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(allergyDTO);
    }

    /**
     * {@code DELETE  /allergies/:id} : delete the "id" allergy.
     *
     * @param id the id of the allergyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/allergies/{id}")
    public ResponseEntity<Void> deleteAllergy(@PathVariable Long id) {
        log.debug("REST request to delete Allergy : {}", id);
        allergyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * Get a Generic Name
     *
     * @param genericName
     * @return the ResponseEntity with status 200 (OK) and the list of notificationHistories in body
     */
    @GetMapping("/allergy/by-generic-name")
    public ResponseEntity<AllergyDTO> getAllergyByGenericName(@RequestBody String genericName) {
        log.debug("REST request to delete SideEffect : {}");
        Optional<AllergyDTO> allergyDTO = allergyService.findAllergyByGenericName(genericName);
        return ResponseUtil.wrapOrNotFound(allergyDTO);
    }

    /**
     * Get List of Generic Name
     *
     * @param pageable
     * @param genericName
     * @param uriBuilder
     * @return the ResponseEntity with status 200 (OK) and the list of notificationHistories in body
     */
    @GetMapping("/allergy/by-generic-name-list")
    public ResponseEntity<List<AllergyDTO>> getAllergyByGenericNameList(Pageable pageable,
                                                                        @RequestBody List<String> genericName, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to delete SideEffect : {}");
        Page<AllergyDTO> page = allergyService.findAllergyByGenericNameList(pageable, genericName);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder, page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
