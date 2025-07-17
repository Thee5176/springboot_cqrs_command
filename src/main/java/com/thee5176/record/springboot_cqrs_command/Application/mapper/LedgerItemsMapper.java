package com.thee5176.record.springboot_cqrs_command.Application.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.LedgerItems;

@Service
public class LedgerItemsMapper {
    private final ModelMapper modelMapper;

    public LedgerItemsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LedgerItems> map(CreateRecordDTO createRecordDTO) {
        
        return createRecordDTO.getLedgerItems().stream()
            .map(ledgerItemsDto -> this.modelMapper.map(ledgerItemsDto, LedgerItems.class))
            .map(ledgerItems -> {
                ledgerItems.setCreatedAt(createRecordDTO.getTimestamp());
                ledgerItems.setUpdatedAt(createRecordDTO.getTimestamp());
                return ledgerItems;
            })
            .collect(Collectors.toList());
    }
}