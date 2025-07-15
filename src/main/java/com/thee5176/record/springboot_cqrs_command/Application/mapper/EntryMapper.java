package com.thee5176.record.springboot_cqrs_command.Application.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateEntryDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateTransactionDTO;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Entries;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Transactions;

@Service
public class EntryMapper {
    final ModelMapper modelMapper;

    public EntryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Transactions map(CreateTransactionDTO createTransactionDTO) {
        return modelMapper.map(createTransactionDTO, Transactions.class);
    }

    public Entries map(CreateEntryDTO createEntryDTO) {
        return modelMapper.map(createEntryDTO, Entries.class);
    }
}