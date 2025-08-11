package edu.mikita.apiintegration.mapper;

import edu.mikita.apiintegration.dto.StockDto;
import edu.mikita.apiintegration.model.Stock;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockDto toDto(Stock stock);

    List<StockDto> toDto(List<Stock> stocks);
}
