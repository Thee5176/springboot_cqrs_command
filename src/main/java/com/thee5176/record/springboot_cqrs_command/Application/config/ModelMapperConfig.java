package com.thee5176.record.springboot_cqrs_command.Application.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateEntryDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Entries;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Transactions;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.LOOSE)
            .setFieldMatchingEnabled(true);       
        // CreateEntryDTO map to Entries
        modelMapper.createTypeMap(CreateEntryDTO.class, Entries.class)
            .addMapping(CreateEntryDTO::getCoa, Entries::setCoa)
            .addMapping(CreateEntryDTO::getAmount, Entries::setAmount)
            .addMapping(CreateEntryDTO::getType, Entries::setType)
            .addMappings(mapper -> mapper.skip(Entries::setId))
            .addMappings(mapper -> mapper.skip(Entries::setTransactionId))
            .addMappings(mapper -> mapper.skip(Entries::setCreatedAt))
            .addMappings(mapper -> mapper.skip(Entries::setUpdatedAt));

        // CreateRecordDTO map to Transactions
        modelMapper.createTypeMap(CreateRecordDTO.class, Transactions.class)
            .addMapping(CreateRecordDTO::getId, Transactions::setId)
            .addMapping(CreateRecordDTO::getDate, Transactions::setDate)
            .addMapping(CreateRecordDTO::getDescription, Transactions::setDescription)
            .addMappings(mapper -> mapper.skip(Transactions::setCreatedAt))
            .addMappings(mapper -> mapper.skip(Transactions::setUpdatedAt));

        modelMapper.validate();
        return modelMapper;
    }
}