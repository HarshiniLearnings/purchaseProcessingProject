package service;

import org.example.purchaseprocessingproject.model.Purchase;
import org.junit.jupiter.api.Test;
import org.example.purchaseprocessingproject.repository.PurchaseRepoImpl;

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
