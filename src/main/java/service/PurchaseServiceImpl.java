package service;

import dto.PurchaseRequest;
import dto.PurchaseResponse;
import dto.PurchaseResponseObject;
import exception.PurchaseNotFoundException;
import lombok.RequiredArgsConstructor;
import model.CurrencyCode;
import model.Purchase;
import repository.PurchaseRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;

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
