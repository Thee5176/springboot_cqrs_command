package com.thee5176.record.springboot_cqrs_command.Application.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Entries;

@Service
public class EntryMapper {
    private final ModelMapper modelMapper;

    public EntryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<Entries> map(CreateRecordDTO createRecordDTO) {
        
        return createRecordDTO.getEntries().stream()
            .map(entryDto -> this.modelMapper.map(entryDto, Entries.class))
            .map(entry -> {
                entry.setTransactionId(createRecordDTO.getId());
                entry.setUpdatedAt(createRecordDTO.getTimestamp());
                return entry;
            })
            .collect(Collectors.toList());
    }
}