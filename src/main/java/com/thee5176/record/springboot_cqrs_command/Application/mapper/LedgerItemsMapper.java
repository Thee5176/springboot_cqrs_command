package com.thee5176.record.springboot_cqrs_command.Application.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateLedgerDTO;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.LedgerItems;

@Service
public class LedgerItemsMapper {
    private final ModelMapper modelMapper;

    public LedgerItemsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LedgerItems> map(CreateLedgerDTO createLedgerDTO) {
        
        return createLedgerDTO.getLedgerItems().stream()
            .map(ledgerItemsDto -> this.modelMapper.map(ledgerItemsDto, LedgerItems.class))
            .map(ledgerItems -> {
                ledgerItems.setCreatedAt(createLedgerDTO.getTimestamp());
                ledgerItems.setUpdatedAt(createLedgerDTO.getTimestamp());
                return ledgerItems;
            })
            .collect(Collectors.toList());
    }
}