package repository;

import model.Purchase;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseRepository {

    Purchase save(Purchase purchase);
    Optional<Purchase> findById(UUID id);
}
