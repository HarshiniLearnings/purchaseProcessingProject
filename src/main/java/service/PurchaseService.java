package service;

import dto.PurchaseRequest;
import dto.PurchaseResponse;
import dto.PurchaseResponseObject;
import model.CurrencyCode;

import java.util.UUID;

public interface PurchaseService {
    UUID createPurchase(PurchaseRequest request );
    PurchaseResponseObject getPurchase(UUID id, CurrencyCode code);
}
