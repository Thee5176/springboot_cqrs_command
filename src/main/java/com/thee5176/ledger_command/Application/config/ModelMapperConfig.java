package com.thee5176.ledger_command.Application.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.thee5176.ledger_command.Application.dto.CreateLedgerDTO;
import com.thee5176.ledger_command.Application.dto.CreateLedgerItemsDTO;
import com.thee5176.ledger_command.Domain.model.tables.pojos.LedgerItems;
import com.thee5176.ledger_command.Domain.model.tables.pojos.Ledgers;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.LOOSE)
            .setFieldMatchingEnabled(true);       
        
        // CreateLedgerItemsDTO map to LedgerItems
        modelMapper.createTypeMap(CreateLedgerItemsDTO.class, LedgerItems.class)
            .addMapping(CreateLedgerItemsDTO::getCoa, LedgerItems::setCoa)
            .addMapping(CreateLedgerItemsDTO::getAmount, LedgerItems::setAmount)
            .addMapping(CreateLedgerItemsDTO::getType, LedgerItems::setType)
            .addMappings(mapper -> mapper.skip(LedgerItems::setId))
            .addMappings(mapper -> mapper.skip(LedgerItems::setLedgerId))
            .addMappings(mapper -> mapper.skip(LedgerItems::setCreatedAt))
            .addMappings(mapper -> mapper.skip(LedgerItems::setUpdatedAt));
            // Note1: The ID and LedgerId are set in the service layer.
            // Note2: The CreatedAt and UpdatedAt are set in the custom mapper layer,

        // CreateLedgerDTO map to Ledgers
        modelMapper.createTypeMap(CreateLedgerDTO.class, Ledgers.class)
            .addMapping(CreateLedgerDTO::getDate, Ledgers::setDate)
            .addMapping(CreateLedgerDTO::getDescription, Ledgers::setDescription)
            .addMappings(mapper -> mapper.skip(Ledgers::setCreatedAt))
            .addMappings(mapper -> mapper.skip(Ledgers::setUpdatedAt))
            .addMappings(mapper -> mapper.skip(Ledgers::setId));
            // Note1: The CreatedAt and UpdatedAt is set in the custom mapper layer.
            // Note2: The ID is set in the service layer.
            
        modelMapper.validate();
        return modelMapper;
    }
}