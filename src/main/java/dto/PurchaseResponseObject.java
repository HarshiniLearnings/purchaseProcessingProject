package dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PurchaseResponseObject(

        UUID purchaseId,
        String description,
        LocalDate transactionDate,
        BigDecimal originalAmountUsd,
        String targetCurrency,
        BigDecimal exchangeRate,
        BigDecimal convertedAmount
        ) {
}
