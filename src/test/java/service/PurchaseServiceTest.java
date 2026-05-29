package service;


import org.example.purchaseprocessingproject.dto.PurchaseRequest;
import org.example.purchaseprocessingproject.dto.PurchaseResponseObject;
import org.example.purchaseprocessingproject.model.CurrencyCode;
import org.example.purchaseprocessingproject.model.Purchase;
import org.example.purchaseprocessingproject.service.ExchangeRateService;
import org.example.purchaseprocessingproject.service.PurchaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.example.purchaseprocessingproject.repository.PurchaseRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository repository;

    @Mock
    private ExchangeRateService exchangeRateService;

    @InjectMocks
    private PurchaseServiceImpl service;

    @Test
    void testCreatePurchase(){
        PurchaseRequest request = new PurchaseRequest(
                "Tickets",
                LocalDate.now(),
                BigDecimal.valueOf(100)
        );

        UUID id = service.createPurchase(request);

        assertNotNull(id);

        verify(repository).save(any(Purchase.class));
    }

    @Test
    void testGetPurchase(){

        UUID id = UUID.randomUUID();

        Purchase purchase = Purchase.builder()
                .uniqId(id)
                .description("Shoes")
                .transactionDate(LocalDate.now())
                .purchaseAmount(BigDecimal.valueOf(50)).
                build();

        when(repository.findById(id))
                .thenReturn(Optional.of(purchase));

        when(exchangeRateService.getRate(CurrencyCode.EUR, purchase.getTransactionDate()))
                .thenReturn(BigDecimal.valueOf(0.9));

        PurchaseResponseObject response = service.getPurchase(id, CurrencyCode.EUR);

        BigDecimal expected =
                BigDecimal.valueOf(45.00)
                        .setScale(2, RoundingMode.HALF_UP);

        assertEquals(
                0,
                expected.compareTo(response.convertedAmount())
        );

        assertEquals(CurrencyCode.EUR.name(), response.targetCurrency());

        verify(repository, times(1))
                .findById(id);

        verify(exchangeRateService, times(1))
                .getRate(CurrencyCode.EUR, purchase.getTransactionDate());


    }
}
