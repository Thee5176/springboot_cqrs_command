package com.thee5176.record.springboot_cqrs_command.Application.mapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTOTest;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Transactions;

public class TransactionMapperTest {

    @Test
    public void testMap_ShouldMapCreateRecordDTOToTransactions() {
        ModelMapper modelMapper = new ModelMapper();
        TransactionMapper transactionMapper = new TransactionMapper(modelMapper);

        CreateRecordDTO dto = CreateRecordDTOTest.createSampleCreateRecordDTO();

        Transactions transaction = transactionMapper.map(dto);

        assertNotNull(transaction);
        assertNull(transaction.getId()); // ID should be set later in the service
        assertEquals(dto.getDate(),transaction.getDate());
        assertEquals(dto.getDescription(), transaction.getDescription());
        assertEquals(dto.getTimestamp(), transaction.getCreatedAt());
        assertEquals(dto.getTimestamp(), transaction.getUpdatedAt());
    }

    @Test
    public void testMap_NullDTO_ShouldReturnNullFields() {
        ModelMapper modelMapper = new ModelMapper();
        TransactionMapper transactionMapper = new TransactionMapper(modelMapper);

        CreateRecordDTO dto = new CreateRecordDTO();

        Transactions transaction = transactionMapper.map(dto);

        assertNotNull(transaction);
        assertNull(transaction.getDescription());
    }

}