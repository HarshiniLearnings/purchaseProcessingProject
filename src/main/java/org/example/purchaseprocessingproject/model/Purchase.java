package org.example.purchaseprocessingproject.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    private UUID uniqId;
    private String description;
    private LocalDate transactionDate;
    private BigDecimal purchaseAmount;

}
