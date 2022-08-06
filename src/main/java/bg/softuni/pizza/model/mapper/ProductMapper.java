package bg.softuni.pizza.model.mapper;

import org.mapstruct.Mapper;

import bg.softuni.pizza.model.dto.product.ProductDto;
import bg.softuni.pizza.model.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {


  ProductEntity productDtoToProductEntity(ProductDto productDto);
}
