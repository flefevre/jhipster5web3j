package org.flfmitlab.jhipster5web3j.service;

import org.flfmitlab.jhipster5web3j.service.dto.BlockDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Block.
 */
public interface BlockService {

    /**
     * Save a block.
     *
     * @param blockDTO the entity to save
     * @return the persisted entity
     */
    BlockDTO save(BlockDTO blockDTO);

    /**
     * Get all the blocks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BlockDTO> findAll(Pageable pageable);


    /**
     * Get the "id" block.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BlockDTO> findOne(Long id);

    /**
     * Delete the "id" block.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
