package org.example.purchaseprocessingproject.repository;

import org.example.purchaseprocessingproject.model.Purchase;
import java.util.Optional;
import java.util.UUID;

public interface PurchaseRepository {

    Purchase save(Purchase purchase);
    Optional<Purchase> findById(UUID id);
}
