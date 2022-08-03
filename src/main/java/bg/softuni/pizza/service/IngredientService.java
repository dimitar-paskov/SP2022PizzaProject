/**
 * @author dimitar
 *
 */
package bg.softuni.pizza.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import bg.softuni.pizza.model.entity.IngredientEntity;
import bg.softuni.pizza.repository.IngredientRepository;

@Service
public class IngredientService {
	
	private final IngredientRepository ingredientRepository;

	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
		
	}

	public void initIngredients() { 
		
		if(this.ingredientRepository.count() == 0) {
			
			List<IngredientEntity> ingredients = new ArrayList<>(); 
			
			IngredientEntity tomatoJuice = new IngredientEntity();
			tomatoJuice.setName("tomatoJuice"); 
			ingredients.add(tomatoJuice);
			
			IngredientEntity mozzarella = new IngredientEntity();
			mozzarella.setName("mozzarella"); 
			ingredients.add(mozzarella);
			
			IngredientEntity extraMozzarella = new IngredientEntity();
			extraMozzarella.setName("extraMozzarella"); 
			ingredients.add(extraMozzarella);
			
			IngredientEntity ventrichina = new IngredientEntity();
			ventrichina.setName("ventrichina"); 
			ingredients.add(ventrichina);
			
			IngredientEntity peperoni = new IngredientEntity();
			peperoni.setName("peperoni"); 
			ingredients.add(peperoni);
			
			IngredientEntity extraPeperoni = new IngredientEntity();
			extraPeperoni.setName("extraPeperoni"); 
			ingredients.add(extraPeperoni);
			
			IngredientEntity ricotta = new IngredientEntity();
			ricotta.setName("ricotta"); 
			ingredients.add(ricotta);
			
			IngredientEntity freshTomatoes = new IngredientEntity();
			freshTomatoes.setName("freshTomatoes"); 
			ingredients.add(freshTomatoes);
			
			IngredientEntity greenPeppers = new IngredientEntity();
			greenPeppers.setName("greenPeppers"); 
			ingredients.add(greenPeppers);
			
			IngredientEntity fetaCheese = new IngredientEntity();
			fetaCheese.setName("fetaCheese"); 
			ingredients.add(fetaCheese);
			
			IngredientEntity olives = new IngredientEntity();
			olives.setName("olives"); 
			ingredients.add(olives);
			
			IngredientEntity chickenBreastMeat = new IngredientEntity();
			chickenBreastMeat.setName("chickenBreastMeat"); 
			ingredients.add(chickenBreastMeat);
			
			IngredientEntity tomatoes = new IngredientEntity();
			tomatoes.setName("tomatoes"); 
			ingredients.add(tomatoes);
			
			IngredientEntity emental = new IngredientEntity();
			emental.setName("emental"); 
			ingredients.add(emental);
			
			IngredientEntity cream = new IngredientEntity();
			cream.setName("cream"); 
			ingredients.add(cream);
			
			IngredientEntity bacon = new IngredientEntity();
			bacon.setName("bacon"); 
			ingredients.add(bacon);
			
			IngredientEntity mushrooms = new IngredientEntity();
			mushrooms.setName("mushrooms"); 
			ingredients.add(mushrooms);
			
			IngredientEntity pesto = new IngredientEntity();
			pesto.setName("pesto"); 
			ingredients.add(pesto);
			
			IngredientEntity parmigano = new IngredientEntity();
			parmigano.setName("parmigano"); 
			ingredients.add(parmigano);
			
			IngredientEntity basel = new IngredientEntity();
			basel.setName("basel"); 
			ingredients.add(basel);
			
			IngredientEntity onion = new IngredientEntity();
			onion.setName("onion"); 
			ingredients.add(onion);
			
			IngredientEntity pineapple = new IngredientEntity();
			pineapple.setName("pineapple"); 
			ingredients.add(pineapple);
			
			IngredientEntity pasta = new IngredientEntity();
			pasta.setName("pasta"); 
			ingredients.add(pasta);
			
			IngredientEntity iceberg = new IngredientEntity();
			iceberg.setName("iceberg"); 
			ingredients.add(iceberg);
			
			IngredientEntity arugula = new IngredientEntity();
			arugula.setName("arugula"); 
			ingredients.add(arugula);
			
			IngredientEntity corn = new IngredientEntity();
			corn.setName("corn"); 
			ingredients.add(corn);
			
			
			this.ingredientRepository.saveAll(ingredients);
		}
		
		
	}
	
	
	
	

}
