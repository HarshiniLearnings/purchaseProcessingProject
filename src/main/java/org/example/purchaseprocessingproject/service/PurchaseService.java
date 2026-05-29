package org.example.purchaseprocessingproject.service;

import org.example.purchaseprocessingproject.dto.PurchaseRequest;
import org.example.purchaseprocessingproject.dto.PurchaseResponse;
import org.example.purchaseprocessingproject.dto.PurchaseResponseObject;
import org.example.purchaseprocessingproject.model.CurrencyCode;

import java.util.UUID;

public interface PurchaseService {
    UUID createPurchase(PurchaseRequest request );
    PurchaseResponseObject getPurchase(UUID id, CurrencyCode code);
}
