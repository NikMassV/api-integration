package edu.mikita.apiintegration.mapper;

import edu.mikita.apiintegration.dto.StockDto;
import edu.mikita.apiintegration.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    @Mapping(target = "price", expression = "java(roundPrice(stock.getPrice()))")
    StockDto toDto(Stock stock);

    List<StockDto> toDto(List<Stock> stocks);

    default BigDecimal roundPrice(BigDecimal price) {
        if (price == null) {
            return null;
        }
        return price.setScale(2, RoundingMode.HALF_UP);
    }
}
