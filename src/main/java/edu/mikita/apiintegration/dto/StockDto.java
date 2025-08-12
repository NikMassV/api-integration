package edu.mikita.apiintegration.dto;

import java.math.BigDecimal;

public record StockDto(Long id, String symbol, String name, BigDecimal price) {
}
