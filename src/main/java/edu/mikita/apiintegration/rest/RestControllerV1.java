package edu.mikita.apiintegration.rest;

import edu.mikita.apiintegration.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stocks")
public class RestControllerV1 {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<?> getStocks() {
        return ResponseEntity.ok(stockService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }
}
