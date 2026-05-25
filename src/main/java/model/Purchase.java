package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    private UUID uniqId;
    private String description;
    private LocalDate transactionDate;
    private BigDecimal purchaseAmount;

}
