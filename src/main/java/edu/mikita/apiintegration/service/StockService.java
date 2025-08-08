package edu.mikita.apiintegration.service;

import edu.mikita.apiintegration.api.StocksApi;
import edu.mikita.apiintegration.dto.StockDto;
import edu.mikita.apiintegration.exception.ApiUnauthorizedException;
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
    private final RateLimiter catApiRateLimiter;

    public List<StockDto> getAll() {
        Supplier<List<StockDto>> catsSupplier = () -> {
            try {
                var response = stocksApi.getStocks();
                var externalCats = response.getBody();
                return stockMapper.toDto(externalCats);
            } catch (FeignException.Unauthorized e) {
                throw new ApiUnauthorizedException("Missing of incorrect API key");
            }
        };
        return RateLimiter
                .decorateSupplier(catApiRateLimiter, catsSupplier)
                .get();
    }

    public StockDto getStockById(Long id) {
        Supplier<StockDto> catByIdSupplier = () -> {
            var response = stocksApi.getStockById(id);
            var externalCat = response.getBody();
            return stockMapper.toDto(externalCat);
        };
        return RateLimiter
                .decorateSupplier(catApiRateLimiter, catByIdSupplier)
                .get();
    }
}
