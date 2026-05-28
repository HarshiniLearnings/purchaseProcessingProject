package client;

import model.CurrencyCode;
import model.ExchangeRate;

import java.util.List;

public interface TreasuryRate {

    List<ExchangeRate> getRates(CurrencyCode code);
}
