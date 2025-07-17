package com.thee5176.record.springboot_cqrs_command.Application.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Ledgers;

@Service
public class LedgerMapper {
    private final ModelMapper modelMapper;

    public LedgerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Ledgers map(CreateRecordDTO createRecordDTO) {
        Ledgers ledger = this.modelMapper.map(createRecordDTO, Ledgers.class);   
        ledger.setCreatedAt(createRecordDTO.getTimestamp());
        ledger.setUpdatedAt(createRecordDTO.getTimestamp());
        // Note: The ID is set in the service layer.
        return ledger;
    }
}