/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import bg.softuni.pizza.model.dto.order.OrderDto;
import bg.softuni.pizza.model.dto.order.OrderPizzaDto;
import bg.softuni.pizza.model.entity.IngredientEntity;
import bg.softuni.pizza.model.entity.ProductEntity;
import bg.softuni.pizza.model.enums.ProductCategoryEnum;
import bg.softuni.pizza.model.views.ProductView;
import bg.softuni.pizza.repository.IngredientRepository;
import bg.softuni.pizza.repository.ProductRepository;
import bg.softuni.pizza.service.exception.ObjectNotFoundException;



@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	private final IngredientRepository ingredientRepository;

	public ProductService(ProductRepository productRepository, IngredientRepository ingredientRepository) {
		this.productRepository = productRepository;
		this.ingredientRepository = ingredientRepository;  
		
	}
	
    public void initProducts() {
        if (this.productRepository.count() == 0) {
        	
        	List<ProductEntity> products = new ArrayList<>();
        	
            ProductEntity margarita = new ProductEntity();
            margarita.setCategory(ProductCategoryEnum.PIZZA);
            Set<IngredientEntity> ingredients = margarita.getIngredients();             
            ingredients.add(ingredientRepository.findByName("tomatoJuice").get());
            ingredients.add(ingredientRepository.findByName("mozzarella").get());
            ingredients.add(ingredientRepository.findByName("extraMozzarella").get());
            margarita.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659437651/projectPizza/pizzas/margarita_q91th6.png"); 
            margarita.setPrice(BigDecimal.valueOf(10.00));
            margarita.setName("margarita");
            products.add(margarita);
            
            ProductEntity riveroni = new ProductEntity();
            riveroni.setCategory(ProductCategoryEnum.PIZZA);
            ingredients = riveroni.getIngredients();             
            ingredients.add(ingredientRepository.findByName("tomatoJuice").get());
            ingredients.add(ingredientRepository.findByName("mozzarella").get());
            ingredients.add(ingredientRepository.findByName("ventrichina").get());
            ingredients.add(ingredientRepository.findByName("peperoni").get());
            ingredients.add(ingredientRepository.findByName("ricotta").get());
            riveroni.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659437651/projectPizza/pizzas/riveroni_ljomuj.png"); 
            riveroni.setPrice(BigDecimal.valueOf(11.00));
            riveroni.setName("riveroni");
            products.add(riveroni);
            
            ProductEntity chickenita = new ProductEntity();
            chickenita.setCategory(ProductCategoryEnum.PIZZA);
            ingredients = chickenita.getIngredients();             
            ingredients.add(ingredientRepository.findByName("tomatoJuice").get());
            ingredients.add(ingredientRepository.findByName("mozzarella").get());
            ingredients.add(ingredientRepository.findByName("chickenBreastMeat").get());
            ingredients.add(ingredientRepository.findByName("peperoni").get());
            ingredients.add(ingredientRepository.findByName("tomatoes").get());
            ingredients.add(ingredientRepository.findByName("emental").get());
            chickenita.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659437650/projectPizza/pizzas/chickenita_bayiu8.png"); 
            chickenita.setPrice(BigDecimal.valueOf(12.00));
            chickenita.setName("chickenita");
            products.add(chickenita); 
            
            
            ProductEntity carbonara = new ProductEntity();
            carbonara.setCategory(ProductCategoryEnum.PIZZA);
            ingredients = carbonara.getIngredients();             
            ingredients.add(ingredientRepository.findByName("cream").get());
            ingredients.add(ingredientRepository.findByName("mozzarella").get());
            ingredients.add(ingredientRepository.findByName("bacon").get());
            ingredients.add(ingredientRepository.findByName("mushrooms").get());
            carbonara.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659437650/projectPizza/pizzas/carbonara_v41r5i.png"); 
            carbonara.setPrice(BigDecimal.valueOf(13.00));
            carbonara.setName("carbonara");
            products.add(carbonara); 
            
            
            ProductEntity peperoni = new ProductEntity();
            peperoni.setCategory(ProductCategoryEnum.PIZZA);
            ingredients = peperoni.getIngredients();             
            ingredients.add(ingredientRepository.findByName("tomatoJuice").get());
            ingredients.add(ingredientRepository.findByName("mozzarella").get());
            ingredients.add(ingredientRepository.findByName("extraMozzarella").get());
            ingredients.add(ingredientRepository.findByName("peperoni").get());
            ingredients.add(ingredientRepository.findByName("extraPeperoni").get());
            peperoni.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659437651/projectPizza/pizzas/peperoni_boimmt.png"); 
            peperoni.setPrice(BigDecimal.valueOf(14.00));
            peperoni.setName("peperoni");
            products.add(peperoni); 
            
            
            ProductEntity italiana = new ProductEntity();
            italiana.setCategory(ProductCategoryEnum.PIZZA);
            ingredients = italiana.getIngredients();             
            ingredients.add(ingredientRepository.findByName("tomatoJuice").get());
            ingredients.add(ingredientRepository.findByName("mozzarella").get());
            ingredients.add(ingredientRepository.findByName("pesto").get());
            ingredients.add(ingredientRepository.findByName("parmigano").get());
            ingredients.add(ingredientRepository.findByName("freshTomatoes").get());
            ingredients.add(ingredientRepository.findByName("basel").get());
            italiana.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659437650/projectPizza/pizzas/italiana_t8ubpi.png"); 
            italiana.setPrice(BigDecimal.valueOf(15.00));
            italiana.setName("italiana");
            products.add(italiana); 
            
            
            ProductEntity gardenClassic = new ProductEntity();
            gardenClassic.setCategory(ProductCategoryEnum.PIZZA);
            ingredients = gardenClassic.getIngredients();             
            ingredients.add(ingredientRepository.findByName("tomatoJuice").get());
            ingredients.add(ingredientRepository.findByName("mozzarella").get());
            ingredients.add(ingredientRepository.findByName("olives").get());
            ingredients.add(ingredientRepository.findByName("greenPeppers").get());
            ingredients.add(ingredientRepository.findByName("onion").get());
            ingredients.add(ingredientRepository.findByName("freshTomatoes").get());
            ingredients.add(ingredientRepository.findByName("mushrooms").get());
            gardenClassic.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659437650/projectPizza/pizzas/gardenClassik_aabf3u.png"); 
            gardenClassic.setPrice(BigDecimal.valueOf(16.00));
            gardenClassic.setName("gardenClassic");
            products.add(gardenClassic); 
            
            
            ProductEntity hawai = new ProductEntity();
            hawai.setCategory(ProductCategoryEnum.PIZZA);
            ingredients = hawai.getIngredients();             
            ingredients.add(ingredientRepository.findByName("tomatoJuice").get());
            ingredients.add(ingredientRepository.findByName("mozzarella").get());
            ingredients.add(ingredientRepository.findByName("bacon").get());
            ingredients.add(ingredientRepository.findByName("pineapple").get());
            hawai.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659437650/projectPizza/pizzas/hawai_w8yhvl.png"); 
            hawai.setPrice(BigDecimal.valueOf(16.00));
            hawai.setName("hawai");
            products.add(hawai); 
            
            
            ProductEntity pastaCarbonara = new ProductEntity();
            pastaCarbonara.setCategory(ProductCategoryEnum.PASTA);
            ingredients = pastaCarbonara.getIngredients();             
            ingredients.add(ingredientRepository.findByName("pasta").get());
            ingredients.add(ingredientRepository.findByName("cream").get());
            ingredients.add(ingredientRepository.findByName("bacon").get());
            ingredients.add(ingredientRepository.findByName("mushrooms").get());
            ingredients.add(ingredientRepository.findByName("parmigano").get());
            pastaCarbonara.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659454651/projectPizza/pasta/pasta_carbonara_u5wfgu.png"); 
            pastaCarbonara.setPrice(BigDecimal.valueOf(16.00));
            pastaCarbonara.setName("pastaCarbonara");
            products.add(pastaCarbonara); 
            
            
            ProductEntity pastaNapolitana = new ProductEntity();
            pastaNapolitana.setCategory(ProductCategoryEnum.PASTA);
            ingredients = pastaNapolitana.getIngredients();             
            ingredients.add(ingredientRepository.findByName("pasta").get());
            ingredients.add(ingredientRepository.findByName("tomatoJuice").get());
            ingredients.add(ingredientRepository.findByName("pesto").get());
            ingredients.add(ingredientRepository.findByName("parmigano").get());
            pastaNapolitana.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659454651/projectPizza/pasta/pasta_napolitana_x2dte0.png"); 
            pastaNapolitana.setPrice(BigDecimal.valueOf(16.00));
            pastaNapolitana.setName("pastaNapolitana");
            products.add(pastaNapolitana); 
            
            
            ProductEntity pastaPeperoni = new ProductEntity();
            pastaPeperoni.setCategory(ProductCategoryEnum.PASTA);
            ingredients = pastaPeperoni.getIngredients();             
            ingredients.add(ingredientRepository.findByName("pasta").get());
            ingredients.add(ingredientRepository.findByName("tomatoJuice").get());
            ingredients.add(ingredientRepository.findByName("cream").get());
            ingredients.add(ingredientRepository.findByName("peperoni").get());
            ingredients.add(ingredientRepository.findByName("parmigano").get());
            pastaPeperoni.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659454651/projectPizza/pasta/pasta_peperoni_lesbrj.png"); 
            pastaPeperoni.setPrice(BigDecimal.valueOf(16.00));
            pastaPeperoni.setName("pastaPeperoni");
            products.add(pastaPeperoni); 
            
            
            ProductEntity pastaMacAndCheese = new ProductEntity();
            pastaMacAndCheese.setCategory(ProductCategoryEnum.PASTA);
            ingredients = pastaMacAndCheese.getIngredients();             
            ingredients.add(ingredientRepository.findByName("pasta").get());
            ingredients.add(ingredientRepository.findByName("emental").get());
            ingredients.add(ingredientRepository.findByName("cream").get());
            ingredients.add(ingredientRepository.findByName("parmigano").get());
            pastaMacAndCheese.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659454651/projectPizza/pasta/pasta_mac_and_cheese_lbgupu.png"); 
            pastaMacAndCheese.setPrice(BigDecimal.valueOf(16.00));
            pastaMacAndCheese.setName("pastaMacAndCheese");
            products.add(pastaMacAndCheese); 
            
            
            ProductEntity saladRoca = new ProductEntity();
            saladRoca.setCategory(ProductCategoryEnum.SALAD);
            ingredients = saladRoca.getIngredients();             
            ingredients.add(ingredientRepository.findByName("iceberg").get());
            ingredients.add(ingredientRepository.findByName("arugula").get());
            ingredients.add(ingredientRepository.findByName("freshTomatoes").get());
            ingredients.add(ingredientRepository.findByName("parmigano").get());
            ingredients.add(ingredientRepository.findByName("olives").get());
            saladRoca.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659456843/projectPizza/salad/salad_roca_ny8shp.png"); 
            saladRoca.setPrice(BigDecimal.valueOf(16.00));
            saladRoca.setName("saladRoca");
            products.add(saladRoca); 
            
            
            ProductEntity saladTricolore = new ProductEntity();
            saladTricolore.setCategory(ProductCategoryEnum.SALAD);
            ingredients = saladTricolore.getIngredients();             
            ingredients.add(ingredientRepository.findByName("fetaCheese").get());
            ingredients.add(ingredientRepository.findByName("pesto").get());
            ingredients.add(ingredientRepository.findByName("freshTomatoes").get());
            ingredients.add(ingredientRepository.findByName("olives").get());
            saladTricolore.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659456843/projectPizza/salad/salad_tricolore_nv4wdq.png"); 
            saladTricolore.setPrice(BigDecimal.valueOf(16.00));
            saladTricolore.setName("saladTricolore");
            products.add(saladTricolore); 
            
            
            ProductEntity saladCesare = new ProductEntity();
            saladCesare.setCategory(ProductCategoryEnum.SALAD);
            ingredients = saladCesare.getIngredients();             
            ingredients.add(ingredientRepository.findByName("iceberg").get());
            ingredients.add(ingredientRepository.findByName("chickenBreastMeat").get());
            ingredients.add(ingredientRepository.findByName("corn").get());
            ingredients.add(ingredientRepository.findByName("parmigano").get());
            saladCesare.setImageUrl("https://res.cloudinary.com/dofeaskyi/image/upload/v1659456843/projectPizza/salad/salad_cesare_qndqte.png"); 
            saladCesare.setPrice(BigDecimal.valueOf(16.00));
            saladCesare.setName("saladCesare");
            products.add(saladCesare);
            
            
            
            productRepository.saveAll(products);

           
        }
    }

	public List<ProductView> findAllPizzas() { 
		
		return productRepository.findAllByCategory(ProductCategoryEnum.PIZZA).stream()
		.map(this::map)
		.toList();

	}
	
	private ProductView map(ProductEntity entity) {
		
		ProductView productView = new ProductView();
		
		productView.setId(entity.getId());
		productView.setName(entity.getName()); 
		productView.setImageUrl(entity.getImageUrl());
		productView.setPrice(entity.getPrice());
		productView.setCategory(entity.getCategory().name());
		productView.setDescription(String.join(", ", entity.getIngredients().stream().map(i-> i.getName()).toList()));  
		
		
		return productView;
		
		
	}

	public List<ProductView> findAllPasta() {
		
		return productRepository.findAllByCategory(ProductCategoryEnum.PASTA).stream()
				.map(this::map)
				.toList(); 
	}

	public List<ProductView> findAllSalads() { 
		
		return productRepository.findAllByCategory(ProductCategoryEnum.SALAD).stream()
				.map(this::map)
				.toList();
	}

	public OrderDto findAById(Long id) { 
		
		ProductEntity entity =  productRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id));
		
		OrderDto orderDto = null;
		
		if(entity.getCategory().equals(ProductCategoryEnum.PIZZA)) {
			OrderPizzaDto orderPizzaDto = new OrderPizzaDto();
			orderPizzaDto.setId(entity.getId());
			orderPizzaDto.setName(entity.getName()); 
			orderPizzaDto.setImageUrl(entity.getImageUrl());
			orderPizzaDto.setPrice(entity.getPrice());
			orderPizzaDto.setCategory(entity.getCategory().name());
			orderPizzaDto.setDescription(String.join(", ", entity.getIngredients().stream().map(i-> i.getName()).toList())); 
			orderDto = orderPizzaDto;
		}else  {
			orderDto = new OrderDto();
			orderDto.setId(entity.getId());
			orderDto.setName(entity.getName()); 
			orderDto.setImageUrl(entity.getImageUrl());
			orderDto.setPrice(entity.getPrice());
			orderDto.setCategory(entity.getCategory().name());
			orderDto.setDescription(String.join(", ", entity.getIngredients().stream().map(i-> i.getName()).toList())); 
			
		}
		
		return orderDto; 
	}

}
