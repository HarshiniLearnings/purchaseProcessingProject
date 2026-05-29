package org.example.purchaseprocessingproject.service;

import org.example.purchaseprocessingproject.dto.PurchaseRequest;
import org.example.purchaseprocessingproject.dto.PurchaseResponse;
import org.example.purchaseprocessingproject.dto.PurchaseResponseObject;
import org.example.purchaseprocessingproject.exception.PurchaseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.purchaseprocessingproject.model.CurrencyCode;
import org.example.purchaseprocessingproject.model.Purchase;
import org.example.purchaseprocessingproject.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository repository ;
    private final ExchangeRateService exchangeRateService;
    @Override
    public UUID createPurchase(PurchaseRequest request) {
        Purchase purchase = Purchase.builder()
                .uniqId(UUID.randomUUID())
                .description(request.description())
                .transactionDate(request.transactionDate())
                .purchaseAmount(request.amount())
                .build();
        System.out.println(purchase);

        repository.save(purchase);


        return purchase.getUniqId();
    }

    @Override
    public PurchaseResponseObject getPurchase(UUID id, CurrencyCode code) {
        Purchase purchase = repository.findById(id)
                .orElseThrow(PurchaseNotFoundException::new);

        BigDecimal rate = exchangeRateService.getRate(code, purchase.getTransactionDate());

        BigDecimal convertedRate = purchase.getPurchaseAmount()
                .multiply(rate)
                .setScale(2, RoundingMode.HALF_UP);

        return new PurchaseResponseObject(purchase.getUniqId(),
                purchase.getDescription(),
                purchase.getTransactionDate(),
                purchase.getPurchaseAmount(),
                code.name(),
                rate,
                convertedRate);
    }
}
