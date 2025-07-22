package com.thee5176.ledger_command.Application.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thee5176.ledger_command.Application.dto.CreateLedgerDTO;
import com.thee5176.ledger_command.Domain.model.tables.pojos.Ledgers;

@Service
public class LedgerMapper {
    private final ModelMapper modelMapper;

    public LedgerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Ledgers map(CreateLedgerDTO createLedgerDTO) {
        Ledgers ledger = this.modelMapper.map(createLedgerDTO, Ledgers.class);   
        // Note: The ID is set in the service layer.
        return ledger;
    }
}