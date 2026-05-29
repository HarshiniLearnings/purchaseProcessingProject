package org.example.purchaseprocessingproject.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseRequest(

        String description,
        LocalDate transactionDate,
        BigDecimal amount
) {


}
