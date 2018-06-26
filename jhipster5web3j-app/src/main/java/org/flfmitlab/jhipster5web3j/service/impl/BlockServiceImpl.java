package org.flfmitlab.jhipster5web3j.service.impl;

import org.flfmitlab.jhipster5web3j.service.BlockService;
import org.flfmitlab.jhipster5web3j.domain.Block;
import org.flfmitlab.jhipster5web3j.repository.BlockRepository;
import org.flfmitlab.jhipster5web3j.service.dto.BlockDTO;
import org.flfmitlab.jhipster5web3j.service.mapper.BlockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Block.
 */
@Service
@Transactional
public class BlockServiceImpl implements BlockService {

    private final Logger log = LoggerFactory.getLogger(BlockServiceImpl.class);

    private final BlockRepository blockRepository;

    private final BlockMapper blockMapper;

    public BlockServiceImpl(BlockRepository blockRepository, BlockMapper blockMapper) {
        this.blockRepository = blockRepository;
        this.blockMapper = blockMapper;
    }

    /**
     * Save a block.
     *
     * @param blockDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BlockDTO save(BlockDTO blockDTO) {
        log.debug("Request to save Block : {}", blockDTO);
        Block block = blockMapper.toEntity(blockDTO);
        block = blockRepository.save(block);
        return blockMapper.toDto(block);
    }

    /**
     * Get all the blocks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BlockDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Blocks");
        return blockRepository.findAll(pageable)
            .map(blockMapper::toDto);
    }


    /**
     * Get one block by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BlockDTO> findOne(Long id) {
        log.debug("Request to get Block : {}", id);
        return blockRepository.findById(id)
            .map(blockMapper::toDto);
    }

    /**
     * Delete the block by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Block : {}", id);
        blockRepository.deleteById(id);
    }
}
