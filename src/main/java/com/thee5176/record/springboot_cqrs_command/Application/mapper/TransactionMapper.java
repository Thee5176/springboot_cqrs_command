package com.thee5176.record.springboot_cqrs_command.Application.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Transactions;

@Service
public class TransactionMapper {
    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Transactions map(CreateRecordDTO createRecordDTO) {
        Transactions transaction = this.modelMapper.map(createRecordDTO, Transactions.class);   
        transaction.setCreatedAt(createRecordDTO.getTimestamp());
        transaction.setUpdatedAt(createRecordDTO.getTimestamp());
        // Note: The ID is set in the service layer.
        return transaction;
    }
}