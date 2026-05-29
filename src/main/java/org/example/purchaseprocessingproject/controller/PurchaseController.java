package org.example.purchaseprocessingproject.controller;

import org.example.purchaseprocessingproject.dto.PurchaseRequest;
import org.example.purchaseprocessingproject.dto.PurchaseResponse;
import org.example.purchaseprocessingproject.dto.PurchaseResponseObject;
import lombok.RequiredArgsConstructor;
import org.example.purchaseprocessingproject.model.CurrencyCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.purchaseprocessingproject.service.PurchaseService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService service;

    @PostMapping
    public ResponseEntity<PurchaseResponse> createPurchase(@RequestBody PurchaseRequest request){

        UUID id = service.createPurchase(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PurchaseResponse(id));
    }

    @GetMapping("/{id}")
    public PurchaseResponseObject getPurchase(@PathVariable UUID id, @RequestParam CurrencyCode code){
        return service.getPurchase(id, code);
    }

}
