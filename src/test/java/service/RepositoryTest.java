package service;

import model.Purchase;
import org.junit.jupiter.api.Test;
import repository.PurchaseRepoImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepositoryTest {

    @Test
    void testSavePurchase(){
        PurchaseRepoImpl repo = new PurchaseRepoImpl();

        Purchase purchase = Purchase.builder()
                .uniqId(UUID.randomUUID())
                .description("Ticket booking")
                .transactionDate(LocalDate.now())
                .purchaseAmount(BigDecimal.TEN)
                .build();

        repo.save(purchase);

        assertTrue(repo.findById(purchase.getUniqId()).isPresent());

    }
}
