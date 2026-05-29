package org.example.purchaseprocessingproject.service;

import org.example.purchaseprocessingproject.client.TreasuryRate;
import org.example.purchaseprocessingproject.exception.CurrencyConversionException;
import lombok.AllArgsConstructor;
import org.example.purchaseprocessingproject.model.CurrencyCode;
import org.example.purchaseprocessingproject.model.ExchangeRate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExchangeRateServiveImple implements ExchangeRateService{

    private static final int MAX_MONTHS = 6;
    private final TreasuryRate treasuryRate;


    @Override
    public BigDecimal getRate(CurrencyCode code, LocalDate purchaseDate) {

        List<ExchangeRate> rates = treasuryRate.getRates(code);
        ExchangeRate closestRate = null;

        for(ExchangeRate rate : rates){
            LocalDate rateDate = rate.currentDate();

            if(rateDate.isAfter(purchaseDate)){
                continue;
            }

            if(closestRate == null || rateDate.isAfter(closestRate.currentDate())){
                closestRate = rate;
            }
        }

        if(closestRate == null){
            throw new CurrencyConversionException();
        }

        LocalDate cutOffDate = purchaseDate.minusMonths(MAX_MONTHS);
        if(closestRate.currentDate().isBefore(cutOffDate)){
            throw new CurrencyConversionException();
        }

        return closestRate.rate();
    }


}
