package org.example.purchaseprocessingproject.client;

import org.example.purchaseprocessingproject.model.CurrencyCode;
import org.example.purchaseprocessingproject.model.ExchangeRate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TreasuryRateClient implements TreasuryRate{

    @Override
    public List<ExchangeRate> getRates(CurrencyCode code) {
        List<ExchangeRate> rates = new ArrayList<>();

        rates.add(new ExchangeRate(LocalDate.of(2024,12,1), code, BigDecimal.valueOf(0.92)));
        rates.add(new ExchangeRate(LocalDate.of(2024,12,10), code, BigDecimal.valueOf(0.92)));

        return rates;
    }
}
