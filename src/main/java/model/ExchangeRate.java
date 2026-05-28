package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExchangeRate(

        LocalDate currentDate,
        CurrencyCode currency,
        BigDecimal rate
) {
}
