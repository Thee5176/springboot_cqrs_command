package com.thee5176.ledger_command.Domain.validation;

import java.math.BigDecimal;
import java.util.List;
import com.thee5176.ledger_command.Application.dto.CreateLedgerItemsDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class BalanceCheckValidator implements ConstraintValidator<BalanceCheck, List<CreateLedgerItemsDTO>> {
    @Override
	public void initialize(BalanceCheck balanceCheck) {}
	
	@Override
	public boolean isValid(List<CreateLedgerItemsDTO> ledgerItems, ConstraintValidatorContext context) {
		if (ledgerItems.size() < 2) {
			return false;
		}
		return ledgerItems.stream()
            .map(item -> item.getBalance())
			.map(BigDecimal::new)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .compareTo(BigDecimal.ZERO) == 0;
	}
}
