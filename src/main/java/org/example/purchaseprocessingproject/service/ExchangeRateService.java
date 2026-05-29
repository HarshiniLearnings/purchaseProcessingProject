package org.example.purchaseprocessingproject.service;

import org.example.purchaseprocessingproject.model.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface ExchangeRateService {

    BigDecimal getRate
            (CurrencyCode code, LocalDate purchaseDate);

}
