package service;

import org.example.purchaseprocessingproject.client.TreasuryRate;
import org.example.purchaseprocessingproject.exception.CurrencyConversionException;
import org.example.purchaseprocessingproject.model.CurrencyCode;
import org.example.purchaseprocessingproject.model.ExchangeRate;
import org.example.purchaseprocessingproject.service.ExchangeRateServiveImple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {

    @Mock
    private TreasuryRate client;

    @InjectMocks
    private ExchangeRateServiveImple service;



    @Test
    void testGetRate() {

        ExchangeRate rate =
                new ExchangeRate(
                        LocalDate.of(2024,12,10),
                        CurrencyCode.EUR,
                        BigDecimal.valueOf(0.92));

        when(client.getRates(CurrencyCode.EUR))
                .thenReturn(List.of(rate));

        BigDecimal result =
                service.getRate(
                        CurrencyCode.EUR,
                        LocalDate.of(2024,12,15));

        assertEquals(
                BigDecimal.valueOf(0.92),
                result);
    }


}
