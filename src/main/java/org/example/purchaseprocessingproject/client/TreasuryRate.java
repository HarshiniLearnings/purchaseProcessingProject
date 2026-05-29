package org.example.purchaseprocessingproject.client;

import org.example.purchaseprocessingproject.model.CurrencyCode;
import org.example.purchaseprocessingproject.model.ExchangeRate;

import java.util.List;

public interface TreasuryRate {

    List<ExchangeRate> getRates(CurrencyCode code);
}
