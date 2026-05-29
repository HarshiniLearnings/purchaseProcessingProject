package org.example.purchaseprocessingproject.repository;

import org.example.purchaseprocessingproject.model.Purchase;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PurchaseRepoImpl implements PurchaseRepository{

    private final Map<UUID, Purchase> purchaseMap = new ConcurrentHashMap<>();

    @Override
    public Purchase save(Purchase purchase) {
        purchaseMap.put(purchase.getUniqId(), purchase);
        return purchase;
    }

    @Override
    public Optional<Purchase> findById(UUID id) {
        return Optional.ofNullable(
                purchaseMap.get(id)
        );
    }
}
