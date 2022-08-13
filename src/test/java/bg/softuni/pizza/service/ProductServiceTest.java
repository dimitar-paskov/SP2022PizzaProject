/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bg.softuni.pizza.model.dto.order.OrderDto;
import bg.softuni.pizza.model.entity.IngredientEntity;
import bg.softuni.pizza.model.entity.ProductEntity;
import bg.softuni.pizza.model.enums.ProductCategoryEnum;
import bg.softuni.pizza.model.mapper.ProductMapper;
import bg.softuni.pizza.model.views.ProductView;
import bg.softuni.pizza.repository.IngredientRepository;
import bg.softuni.pizza.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@Mock
	private IngredientRepository mockIngredientRepository;
	
	@Mock
	private ProductRepository mockProductRepository;
	
	@Mock
	private  CloudinaryService mockCloudinaryService;
	
	@Mock
	private  ProductMapper mockProductMapper;
	
	private ProductService toTest;
	
	private static final IngredientEntity ingr1, ingr2, ingr3, ingr4, ingr5, ingr6;
	
	static{
		ingr1 = new IngredientEntity();
		ingr1.setId(1L);
		ingr1.setName("tomatoes");
		
		ingr2 = new IngredientEntity();
		ingr2.setId(2L);
		ingr2.setName("chesee");
		
		ingr3 = new IngredientEntity();
		ingr3.setId(3L);
		ingr3.setName("basil");
		
		ingr4 = new IngredientEntity();
		ingr4.setId(4L);
		ingr4.setName("meat");
		
		ingr5 = new IngredientEntity();
		ingr5.setId(5L);
		ingr5.setName("chicken");
		
		ingr6 = new IngredientEntity();
		ingr6.setId(6L);
		ingr6.setName("corn");
		
	}
	
	@BeforeEach
	void setUp() {
		toTest = new ProductService( mockProductRepository, mockIngredientRepository, mockCloudinaryService, mockProductMapper );
		
	}
	
	@Test
	void test_findAllPizzas_shouldReturnAllPizzas() {
				
		ProductEntity pizza1 = new ProductEntity();
		pizza1.setCategory(ProductCategoryEnum.PIZZA);
		pizza1.setName("pizza1");
		pizza1.setImageUrl("http://image.com/image");
		pizza1.setPrice(BigDecimal.valueOf(20.00));
		pizza1.setId(1L);
		pizza1.setIngredients(List.of(ingr1, ingr2));
		
		
		ProductEntity pizza2 = new ProductEntity();
		pizza2.setCategory(ProductCategoryEnum.PIZZA);
		pizza2.setName("pizza2");
		pizza2.setImageUrl("http://image.com/image");
		pizza2.setPrice(BigDecimal.valueOf(21.00));
		pizza2.setId(2L);
		pizza2.setIngredients(List.of(ingr3, ingr4));
		
		ProductEntity pizza3 = new ProductEntity();
		pizza3.setCategory(ProductCategoryEnum.PIZZA);
		pizza3.setName("pizza3");
		pizza3.setImageUrl("http://image.com/image");
		pizza3.setPrice(BigDecimal.valueOf(19.00));
		pizza3.setId(3L);
		pizza3.setIngredients(List.of(ingr5, ingr6));
		
		
		
		List<ProductEntity> allPizzas = List.of(pizza1, pizza2, pizza3);  
		
		
		when(mockProductRepository.findAllByCategory(ProductCategoryEnum.PIZZA)).thenReturn(allPizzas);
		
		
		List<ProductView> allPizzasReturned = toTest.findAllPizzas(); 
		
		
		Assertions.assertEquals(3, allPizzasReturned.size());
		Assertions.assertTrue(allPizzasReturned.get(0).getName().equals("pizza1"));
		Assertions.assertEquals(1, allPizzasReturned.get(0).getId());
		Assertions.assertTrue(allPizzasReturned.get(1).getName().equals("pizza2"));
		Assertions.assertEquals(2, allPizzasReturned.get(1).getId());
		Assertions.assertTrue(allPizzasReturned.get(2).getName().equals("pizza3"));
		Assertions.assertEquals(3, allPizzasReturned.get(2).getId());
		
	}
	
	@Test
	void test_findAllPasta_shouldReturnAllPasta() {
		

		ProductEntity pasta1 = new ProductEntity();
		pasta1.setCategory(ProductCategoryEnum.PASTA);
		pasta1.setName("pasta1");
		pasta1.setImageUrl("http://image.com/image");
		pasta1.setPrice(BigDecimal.valueOf(20.00));
		pasta1.setId(4L);
		pasta1.setIngredients(List.of(ingr1, ingr2));
		
		
		ProductEntity pasta2 = new ProductEntity();
		pasta2.setCategory(ProductCategoryEnum.PASTA);
		pasta2.setName("pasta2");
		pasta2.setImageUrl("http://image.com/image");
		pasta2.setPrice(BigDecimal.valueOf(21.00));
		pasta2.setId(5L);
		pasta2.setIngredients(List.of(ingr3, ingr4));
		
		ProductEntity pasta3 = new ProductEntity();
		pasta3.setCategory(ProductCategoryEnum.PASTA);
		pasta3.setName("pasta3");
		pasta3.setImageUrl("http://image.com/image");
		pasta3.setPrice(BigDecimal.valueOf(19.00));
		pasta3.setId(6L);
		pasta3.setIngredients(List.of(ingr5, ingr6));
		
		
		
		List<ProductEntity> allPasta = List.of(pasta1, pasta2, pasta3);  
		
		
		when(mockProductRepository.findAllByCategory(ProductCategoryEnum.PASTA)).thenReturn(allPasta);
		
		
		List<ProductView> allPastaReturned = toTest.findAllPasta(); 
		
		
		Assertions.assertEquals(3, allPastaReturned.size());
		Assertions.assertTrue(allPastaReturned.get(0).getName().equals("pasta1"));
		Assertions.assertEquals(4, allPastaReturned.get(0).getId());
		Assertions.assertTrue(allPastaReturned.get(1).getName().equals("pasta2"));
		Assertions.assertEquals(5, allPastaReturned.get(1).getId());
		Assertions.assertTrue(allPastaReturned.get(2).getName().equals("pasta3"));
		Assertions.assertEquals(6, allPastaReturned.get(2).getId());
		
	}
	
	
	@Test
	void test_findAllSalads_shouldReturnAllSalads() {
		
		
		ProductEntity salad1 = new ProductEntity();
		salad1.setCategory(ProductCategoryEnum.SALAD);
		salad1.setName("salad1");
		salad1.setImageUrl("http://image.com/image");
		salad1.setPrice(BigDecimal.valueOf(20.00));
		salad1.setId(7L);
		salad1.setIngredients(List.of(ingr1, ingr2));
		
		
		ProductEntity salad2 = new ProductEntity();
		salad2.setCategory(ProductCategoryEnum.SALAD);
		salad2.setName("salad2");
		salad2.setImageUrl("http://image.com/image");
		salad2.setPrice(BigDecimal.valueOf(21.00));
		salad2.setId(8L);
		salad2.setIngredients(List.of(ingr3, ingr4));
		
		ProductEntity salad3 = new ProductEntity();
		salad3.setCategory(ProductCategoryEnum.SALAD);
		salad3.setName("salad3");
		salad3.setImageUrl("http://image.com/image");
		salad3.setPrice(BigDecimal.valueOf(19.00));
		salad3.setId(9L);
		salad3.setIngredients(List.of(ingr5, ingr6));
		
		
		
		List<ProductEntity> allSalads = List.of(salad1, salad2, salad3);  
		
		
		when(mockProductRepository.findAllByCategory(ProductCategoryEnum.SALAD)).thenReturn(allSalads);
		
		
		List<ProductView> allSaladsReturned = toTest.findAllSalads(); 
		
		
		Assertions.assertEquals(3, allSaladsReturned.size());
		Assertions.assertTrue(allSaladsReturned.get(0).getName().equals("salad1"));
		Assertions.assertEquals(7, allSaladsReturned.get(0).getId());
		Assertions.assertTrue(allSaladsReturned.get(1).getName().equals("salad2"));
		Assertions.assertEquals(8, allSaladsReturned.get(1).getId());
		Assertions.assertTrue(allSaladsReturned.get(2).getName().equals("salad3"));
		Assertions.assertEquals(9, allSaladsReturned.get(2).getId());
		
	}
	
	@Test
	void test_findById_shouldReturnOrderDto() { 
		
		
		ProductEntity salad = new ProductEntity();
		salad.setCategory(ProductCategoryEnum.SALAD);
		salad.setName("salad1");
		salad.setImageUrl("http://image.com/image");
		salad.setPrice(BigDecimal.valueOf(20.00));
		salad.setId(10L);
		salad.setIngredients(List.of(ingr1, ingr2));

		
		
		when(mockProductRepository.findById(10L)).thenReturn(Optional.of(salad));
		
		
		OrderDto orderDto = toTest.findAById(10L);
		
		
		Assertions.assertEquals(10L, orderDto.getId());
		Assertions.assertEquals("salad1", orderDto.getName());
		Assertions.assertEquals("http://image.com/image", orderDto.getImageUrl());
		Assertions.assertEquals(BigDecimal.valueOf(20.00), orderDto.getPrice());
		Assertions.assertEquals(ProductCategoryEnum.SALAD.name(), orderDto.getCategory());
		
		
		
		
	}
	
	

}
