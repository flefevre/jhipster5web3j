package org.flfmitlab.jhipster5web3j.service.mapper;

import org.flfmitlab.jhipster5web3j.domain.*;
import org.flfmitlab.jhipster5web3j.service.dto.TransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Transaction and its DTO TransactionDTO.
 */
@Mapper(componentModel = "spring", uses = {BlockMapper.class})
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {

    @Mapping(source = "block.id", target = "blockId")
    @Mapping(source = "block.address", target = "blockAddress")
    TransactionDTO toDto(Transaction transaction);

    @Mapping(source = "blockId", target = "block")
    Transaction toEntity(TransactionDTO transactionDTO);

    default Transaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }
}
