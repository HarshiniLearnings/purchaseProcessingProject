package service;

import model.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface ExchangeRateService {

    BigDecimal getRate
            (CurrencyCode code, LocalDate purchaseDate);

}
