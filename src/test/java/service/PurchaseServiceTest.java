package service;


import dto.PurchaseRequest;
import model.CurrencyCode;
import model.Purchase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.PurchaseRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository repository;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
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

        var response = service.getPurchase(id, CurrencyCode.EUR);

        assertEquals(BigDecimal.valueOf(90.00).setScale(2), response.convertedAmount());

    }
}
