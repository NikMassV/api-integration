package edu.mikita.apiintegration.service;

import edu.mikita.apiintegration.api.StocksApi;
import edu.mikita.apiintegration.dto.StockDto;
import edu.mikita.apiintegration.exception.StockApiUnauthorizedException;
import edu.mikita.apiintegration.mapper.StockMapper;
import feign.FeignException;
import io.github.resilience4j.ratelimiter.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockMapper stockMapper;
    private final StocksApi stocksApi;
    private final RateLimiter apiRateLimiter;

    public List<StockDto> getAll() {
        Supplier<List<StockDto>> stocksSupplier = () -> {
            try {
                var response = stocksApi.getStocks();
                var externalStocks = response.getBody();
                return stockMapper.toDto(externalStocks);
            } catch (FeignException.Unauthorized e) {
                throw new StockApiUnauthorizedException("Missing of incorrect API key");
            }
        };
        return RateLimiter
                .decorateSupplier(apiRateLimiter, stocksSupplier)
                .get();
    }

    public StockDto getStockById(Long id) {
        Supplier<StockDto> stockByIdSupplier = () -> {
            var response = stocksApi.getStockById(id);
            var externalStock = response.getBody();
            return stockMapper.toDto(externalStock);
        };
        return RateLimiter
                .decorateSupplier(apiRateLimiter, stockByIdSupplier)
                .get();
    }
}
